package net.epicfight_dd.api.animation;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class JointTrack {
    public static Vec3 getJointWithTranslation(LocalPlayer renderer, LivingEntity ent, Vec3f translation, Joint joint) {
        if (renderer != null && ent != null && translation != null) {
            if (renderer.level().isClientSide) {
                LivingEntityPatch entitypatch = EpicFightCapabilities.getEntityPatch(ent,LivingEntityPatch.class);
                if (entitypatch != null) {
                    float interpolation = 0.0F;
                    OpenMatrix4f transformMatrix;
                    transformMatrix = entitypatch.getArmature().getBoundTransformFor(entitypatch.getAnimator().getPose(interpolation), joint);
                    transformMatrix.translate(translation);
                    OpenMatrix4f.mul((new OpenMatrix4f()).rotate(-((float) Math.toRadians((double) (((LivingEntity) entitypatch.getOriginal()).yBodyRotO + 180.0F))), new Vec3f(0.0F, 1.0F, 0.0F)), transformMatrix, transformMatrix);
                    return new Vec3(
                            (double) transformMatrix.m30 + (entitypatch.getOriginal()).getX(),
                            (double) transformMatrix.m31 + ((entitypatch.getOriginal()).getY() + (ent.getBbHeight() / 1.8) - 1),
                            (double) transformMatrix.m32 + (entitypatch.getOriginal()).getZ()
                    );
                }
            }
        }
        return null;
    }
}
