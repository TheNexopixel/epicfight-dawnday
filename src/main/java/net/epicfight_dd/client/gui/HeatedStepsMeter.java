package net.epicfight_dd.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.epicfight_dd.Epicfight_dd;
import net.epicfight_dd.skill.SkillDataKeyZ;
import net.epicfight_dd.skill.dodge.HeatedStepsRules;
import net.epicfight_dd.skill.dodge.HeatedStepsSkill;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.client.CPSkillRequest;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

@Mod.EventBusSubscriber(modid = Epicfight_dd.MODID, value = Dist.CLIENT)
public final class HeatedStepsMeter {
    private static final ResourceLocation METER = texture("charge_bar");
    private static final ResourceLocation TRIGGER = texture("charge_bar_trigger");
    private static final ResourceLocation SELECTED = texture("charge_bar_trigger_activated");
    private HeatedStepsMeter() { }

    @SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        var mc = Minecraft.getInstance();
        if (mc.player == null) return;
        var patch = EpicFightCapabilities.getLocalPlayerPatch(mc.player);
        if (patch == null) return;
        var container = patch.getSkill(SkillSlots.DODGE);
        if (!(container.getSkill() instanceof HeatedStepsSkill skill) || !patch.isHoldingSkill(skill)) return;
        if (mc.screen == null && mc.player.isAlive() && patch.isEpicFightMode()) return;
        EpicFightNetworkManager.sendToServer(new CPSkillRequest(SkillSlots.DODGE, CPSkillRequest.WorkType.CANCEL));
        patch.resetHolding();
        ClientEngine.getInstance().controlEngine.releaseAllServedKeys();
        container.getDataManager().setData(SkillDataKeyZ.HEATED_STEPS_RESULT_UNTIL.get(), 0);
    }

    @SubscribeEvent
    public static void render(RenderGuiEvent.Post event) {
        var mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null || mc.options.hideGui || mc.player.isSpectator()) return;
        var patch = EpicFightCapabilities.getLocalPlayerPatch(mc.player);
        if (patch == null) return;
        var container = patch.getSkill(SkillSlots.DODGE);
        if (!(container.getSkill() instanceof HeatedStepsSkill skill)) return;
        boolean holding = patch.isHoldingSkill(skill);
        float ticks = holding ? patch.getSkillChargingTicks(event.getPartialTick())
                : container.getDataManager().getDataValue(SkillDataKeyZ.HEATED_STEPS_RESULT.get());
        if (ticks < HeatedStepsRules.TAP_TICKS || !holding && mc.player.tickCount
                >= container.getDataManager().getDataValue(SkillDataKeyZ.HEATED_STEPS_RESULT_UNTIL.get())) return;
        var graphics = event.getGuiGraphics();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1, 1, 1, 1);
        float scale = 1.5F;
        graphics.pose().pushPose();
        graphics.pose().translate((mc.getWindow().getGuiScaledWidth() - 64 * scale) / 2,
                mc.getWindow().getGuiScaledHeight() - 75, 0);
        graphics.pose().scale(scale, scale, 1);
        graphics.blit(METER, 0, 0, 0, 0, 64, 8, 64, 8);
        graphics.pose().translate(HeatedStepsRules.marker(ticks) - 3.5F, -2.5F, 0);
        graphics.blit(holding ? TRIGGER : SELECTED, 0, 0, 0, 0, 7, 13, 7, 13);
        graphics.pose().popPose();
    }

    private static ResourceLocation texture(String name) {
        return ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, "textures/gui/" + name + ".png");
    }
}
