package F.epicfight_dd.skill;

import F.epicfight_dd.Epicfight_dd;
import F.epicfight_dd.skill.stances.WingStanceSkill;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.utils.PacketBufferCodec;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;


public class SkillDataKeyZ {

    private SkillDataKeyZ(){}

    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(ResourceLocation.fromNamespaceAndPath(EpicFightMod.MODID,"skill_data_keys"), Epicfight_dd.MODID);

    public static final RegistryObject<SkillDataKey<Boolean>> SPECIAL_STANCE_ACTIVATE = DATA_KEYS.register("special_stance_active", () -> SkillDataKey.createSkillDataKey(PacketBufferCodec.BOOLEAN, false,true, WingStanceSkill.class));




}
