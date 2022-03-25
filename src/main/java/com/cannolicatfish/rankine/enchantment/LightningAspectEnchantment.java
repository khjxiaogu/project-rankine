package com.cannolicatfish.rankine.enchantment;

import com.cannolicatfish.rankine.init.RankineEnchantmentTypes;
import com.cannolicatfish.rankine.items.tools.HammerItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;

public class LightningAspectEnchantment extends Enchantment {
    public LightningAspectEnchantment(Enchantment.Rarity p_i46730_1_, EquipmentSlot... p_i46730_2_) {
        super(p_i46730_1_, RankineEnchantmentTypes.HAMMER, p_i46730_2_);
    }

    public int getMinCost(int p_77321_1_) {
        return 15;
    }

    public int getMaxCost(int p_223551_1_) {
        return super.getMinCost(p_223551_1_) + 50;
    }

    public int getMaxLevel() {
        return 1;
    }
}