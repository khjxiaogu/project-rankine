package com.cannolicatfish.rankine.util.colors;

import com.cannolicatfish.rankine.items.alloys.AlloyItem;
import com.cannolicatfish.rankine.items.alloys.IAlloyItem;
import com.cannolicatfish.rankine.util.PeriodicTableUtils;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class AlloyItemColor implements ItemColor {
    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        if (stack.getTag() != null && stack.getTag().contains("color")) {
            return stack.getTag().getInt("color");
        }
        return 16777215;
    }
}
