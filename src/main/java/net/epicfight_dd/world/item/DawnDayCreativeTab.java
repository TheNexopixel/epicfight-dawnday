package net.epicfight_dd.world.item;

import net.epicfight_dd.EpicFightDawnDay;
import net.epicfight_dd.skill.DawnDaySkills;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.item.EpicFightItems;
import yesman.epicfight.world.item.SkillBookItem;

public class DawnDayCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EpicFightDawnDay.MODID);

    public static final RegistryObject<CreativeModeTab> DAWN_DAY_TAB = CREATIVE_MODE_TABS.register("epicfight_dd_tab", () -> CreativeModeTab.builder().icon(() ->
                    new ItemStack(DawnDayItems.MILADY.get()))
            .title(Component.translatable("creativetab.dawn_day_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(DawnDayItems.MILADY.get());
                output.accept(DawnDayItems.BONECUTTING_SAW.get());
                output.accept(DawnDayItems.POLEBLADE.get());
                output.accept(DawnDayItems.VITREUS.get());
                output.accept(DawnDayItems.CRESCENDO.get());
             //   output.accept(DawnDayItems.ECLIPSE.get());
                output.accept(DawnDayItems.IRON_FIST.get());
                output.accept(DawnDayItems.GLORIA.get());
                output.accept(DawnDayItems.SCYTHE.get());
                output.accept(DawnDayItems.IUDEX_HALBERD.get());
                output.accept(DawnDayItems.BLOOD_RITUS_DAGGER.get());
                output.accept(DawnDayItems.NIGHT_RITUS_DAGGER.get());
                output.accept(DawnDayItems.SABER.get());
                output.accept(DawnDayItems.BACKHAND_BLADE.get());
                output.accept(DawnDayItems.HORUS_FANG.get());
                output.accept(DawnDayItems.WAR_SICKLE.get());
                output.accept(DawnDayItems.SPIKED_BAT.get());
                output.accept(DawnDayItems.BAT.get());
                output.accept(DawnDayItems.HOOKCLAWS.get());
                output.accept(DawnDayItems.NIGHTINGALE.get());
                output.accept(DawnDayItems.GRIMM.get());
                output.accept(DawnDayItems.TWINBLADE.get());
                output.accept(DawnDayItems.NETHERITE_SICKLE.get());
                output.accept(DawnDayItems.DIAMOND_SICKLE.get());
                output.accept(DawnDayItems.IRON_SICKLE.get());
                output.accept(DawnDayItems.GOLDEN_SICKLE.get());
                output.accept(DawnDayItems.STONE_SICKLE.get());
                output.accept(DawnDayItems.NETHERITE_FLORETT.get());
                output.accept(DawnDayItems.DIAMOND_FLORETT.get());
                output.accept(DawnDayItems.IRON_FLORETT.get());
                output.accept(DawnDayItems.GOLDEN_FLORETT.get());
                output.accept(DawnDayItems.STONE_FLORETT.get());
                output.accept(DawnDayItems.NETHERITE_MESSER.get());
                output.accept(DawnDayItems.DIAMOND_MESSER.get());
                output.accept(DawnDayItems.IRON_MESSER.get());
                output.accept(DawnDayItems.GOLDEN_MESSER.get());
                output.accept(DawnDayItems.STONE_MESSER.get());
                output.accept(DawnDayItems.NETHERITE_LIGHT_GREATSWORD.get());
                output.accept(DawnDayItems.DIAMOND_LIGHT_GREATSWORD.get());
                output.accept(DawnDayItems.IRON_LIGHT_GREATSWORD.get());
                output.accept(DawnDayItems.GOLDEN_LIGHT_GREATSWORD.get());
                output.accept(DawnDayItems.STONE_LIGHT_GREATSWORD.get());
                output.accept(DawnDayItems.NETHERITE_HALBERD.get());
                output.accept(DawnDayItems.DIAMOND_HALBERD.get());
                output.accept(DawnDayItems.IRON_HALBERD.get());
                output.accept(DawnDayItems.GOLDEN_HALBERD.get());
                output.accept(DawnDayItems.STONE_HALBERD.get());
                output.accept(DawnDayItems.BLOOD_KNIFE.get());
                output.accept(DawnDayItems.NETHERITE_KNIFE.get());
                output.accept(DawnDayItems.DIAMOND_KNIFE.get());
                output.accept(DawnDayItems.IRON_KNIFE.get());
                output.accept(DawnDayItems.GOLDEN_KNIFE.get());
                output.accept(DawnDayItems.STONE_KNIFE.get());
                output.accept(DawnDayItems.NETHERITE_BATTLESTAFF.get());
                output.accept(DawnDayItems.AMETHYST_BATTLESTAFF.get());
                output.accept(DawnDayItems.DIAMOND_BATTLESTAFF.get());
                output.accept(DawnDayItems.IRON_BATTLESTAFF.get());
                output.accept(DawnDayItems.GOLDEN_BATTLESTAFF.get());
                output.accept(DawnDayItems.STONE_BATTLESTAFF.get());
                output.accept(DawnDayItems.WOODEN_BATTLESTAFF.get());
                output.accept(DawnDayItems.REVENANT.get());
                output.accept(DawnDayItems.RUBY.get());
                output.accept(DawnDayItems.AMBER.get());
                output.accept(DawnDayItems.CITRINE.get());
                output.accept(DawnDayItems.PERIDOT.get());
                output.accept(DawnDayItems.MALACHITE.get());
                output.accept(DawnDayItems.JADE.get());
                output.accept(DawnDayItems.TANZANITE.get());
                output.accept(DawnDayItems.NAILS.get());
                output.accept(DawnDayItems.METAL_PLATE.get());
                output.accept(DawnDayItems.RUSTED_METAL_PLATE.get());

                ItemStack book_heated_steps = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.HEATED_STEPS, book_heated_steps);
                output.accept(book_heated_steps);
                ItemStack book_cortisol = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.CORTISOL, book_cortisol);
                output.accept(book_cortisol);
                ItemStack book_divine_punishment = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.DIVINE_PUNISHMENT, book_divine_punishment);
                output.accept(book_divine_punishment);

                ItemStack book_final_strength = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.FINAL_STRENGTH, book_final_strength);
                output.accept(book_final_strength);

                ItemStack book_outlasting = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.OUTLASTING, book_outlasting);
                output.accept(book_outlasting);

                ItemStack book_rapid_retreat = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.RAPID_RETREAT, book_rapid_retreat);
                output.accept(book_rapid_retreat);
                ItemStack book_unstoppable = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.UNSTOPPABLE, book_unstoppable);
                output.accept(book_unstoppable);
                ItemStack book_iron_will = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.RIPOSTE, book_iron_will);
                output.accept(book_iron_will);
                ItemStack boock_restocking = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.RESTOCKING, boock_restocking);
                output.accept(boock_restocking);
                ItemStack book_air_dash = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.AIR_DASH, book_air_dash);
                output.accept(book_air_dash);
                ItemStack book_gorged_bulwark = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.GORGED_BULWARK, book_gorged_bulwark);
                output.accept(book_gorged_bulwark);
                ItemStack book_red = new ItemStack(EpicFightItems.SKILLBOOK.get());
                SkillBookItem.setContainingSkill(DawnDaySkills.REDEMPTION, book_red);
                output.accept(book_red);


                // Potions

                // NORMAL
                output.accept(DawnDayItems.ENERGY_DRINK.get());
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.FORTIFIED.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.FORTIFIED_STRONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.FORTIFIED_LONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.STUNARMOR.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.STUNARMOR_STRONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.STUNARMOR_LONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.STAMINAREGEN.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.STAMINAREGEN_STRONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.STAMINAREGEN_LONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.MAXSTAMINA.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.MAXSTAMINA_STRONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.MAXSTAMINA_LONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.POWERPOTION.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.IMPACTPOTION.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.IMPACTPOTION_STRONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.IMPACTPOTION_LONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), DawnDayItems.SWEEPING.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CRUMBLING.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CRUMBLING_STRONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CRUMBLING_LONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CURSED.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CURSED_STRONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.CURSED_LONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.HEART_STOP.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.HEART_STOP_LONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.HEART_STOP_STRONG.get()));
                /*
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.EXHAUSTED.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.EXHAUSTED_STRONG.get()));
                output.accept(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), DawnDayItems.EXHAUSTED_LONG.get()));
                 */
            })
            .build()
    );
}
