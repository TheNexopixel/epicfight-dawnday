package F.epicfight_dd.gameasset.animation.keys;

import F.epicfight_dd.Epicfight_dd;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.animation.SynchedAnimationVariableKey;
import yesman.epicfight.api.utils.PacketBufferCodec;
import yesman.epicfight.main.EpicFightMod;

import java.util.function.Supplier;

public class CustomSynchedVariableKeys_EFDD {

    private static final Supplier<RegistryBuilder<SynchedAnimationVariableKey<?>>> BUILDER = () -> new RegistryBuilder<SynchedAnimationVariableKey<?>>().addCallback(SynchedAnimationVariableKey.getRegistryCallback());

    public static final DeferredRegister<SynchedAnimationVariableKey<?>> SYNCHED_ANIMATION_VARIABLE_KEYS = DeferredRegister.create(EpicFightMod.identifier("custom_synched_variable_keys"), EpicFightMod.MODID);

    public static final Supplier<IForgeRegistry<SynchedAnimationVariableKey<?>>> REGISTRY = SYNCHED_ANIMATION_VARIABLE_KEYS.makeRegistry(BUILDER);

    public static final RegistryObject<SynchedAnimationVariableKey.SynchedIndependentAnimationVariableKey<Integer>> DEATH_VARIANCE = SYNCHED_ANIMATION_VARIABLE_KEYS.register("death_variance", () ->
            SynchedAnimationVariableKey.independent(animator -> -1, true, PacketBufferCodec.INTEGER));


}
