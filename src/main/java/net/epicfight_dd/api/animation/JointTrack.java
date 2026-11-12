package net.epicfight_dd.api.animation;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public final class JointTrack {
    private static final Vec3f UP = new Vec3f(0.0F, 1.0F, 0.0F);
    private static final Vec3f ZERO = new Vec3f(0.0F, 0.0F, 0.0F);

    private JointTrack() {}

    public static Vec3 getJointWithTranslation(LivingEntity ent, Vec3f translation, Joint joint, float partialTick) {
        if (ent == null || joint == null || !ent.level().isClientSide) return null;

        LivingEntityPatch<?> patch = EpicFightCapabilities.getEntityPatch(ent, LivingEntityPatch.class);
        if (patch == null || patch.getArmature() == null || patch.getAnimator() == null) return null;

        OpenMatrix4f transform = patch.getArmature()
                .getBoundTransformFor(patch.getAnimator().getPose(partialTick), joint);
        transform.translate(translation != null ? translation : ZERO);

        float yRot = Mth.lerp(partialTick, ent.yBodyRotO, ent.yBodyRot);
        OpenMatrix4f rotation = new OpenMatrix4f().rotate(-((float) Math.toRadians(yRot + 180.0F)), UP);
        OpenMatrix4f.mul(rotation, transform, transform);

        double x = Mth.lerp(partialTick, ent.xo, ent.getX());
        double y = Mth.lerp(partialTick, ent.yo, ent.getY());
        double z = Mth.lerp(partialTick, ent.zo, ent.getZ());

        return new Vec3(
                transform.m30 + x,
                transform.m31 + y + (ent.getBbHeight() / 1.8D) - 1.0D,
                transform.m32 + z);
    }

    public static Vec3 getJoint(LivingEntity ent, Joint joint, float partialTick) {
        return getJointWithTranslation(ent, ZERO, joint, partialTick);
    }

    @Deprecated
    public static Vec3 getJointWithTranslation(LocalPlayer renderer, LivingEntity ent, Vec3f translation, Joint joint) {
        return getJointWithTranslation(ent, translation, joint, 1.0F);
    }
}