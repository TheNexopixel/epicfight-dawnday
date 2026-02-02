package F.epicfight_dd.world.capabilities.item;

import net.minecraft.world.item.Item;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.function.Function;

public class MiladyWeaponCategories {
    public enum MiladyWeaponCategories implements WeaponCategory, Function<Item, CapabilityItem.Builder> {
        milady;

        final int id;

        MiladyWeaponCategories() {
            this.id = WeaponCategory.ENUM_MANAGER.assign(this);
        }

        @Override
        public int universalOrdinal() {
            return this.id;
        }

        @Override
        public CapabilityItem.Builder apply(Item item) {
            return null;
        }
    }
    @Override
    public CapabilityItem.Builder apply(Item item) {
        return WeaponCategoryMapper.apply(item, this);
    }
    }
