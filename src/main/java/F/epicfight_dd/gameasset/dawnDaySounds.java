package F.epicfight_dd.gameasset;

import F.epicfight_dd.Epicfight_dd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class dawnDaySounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "t0001");


    public static final RegistryObject<SoundEvent> Milady_light_slash = registerSound("sfx.milady_slash");
    public static final RegistryObject<SoundEvent> Milady_heavy_slash = registerSound("sfx.milady_heavy_slash");
    public static final RegistryObject<SoundEvent> Milady_dual_slash = registerSound("sfx.milady_dual_slash");



    private static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation res = ResourceLocation.fromNamespaceAndPath(Epicfight_dd.MODID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(res));
    }
}
