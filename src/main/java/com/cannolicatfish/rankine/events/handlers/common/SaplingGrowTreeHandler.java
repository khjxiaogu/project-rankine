package com.cannolicatfish.rankine.events.handlers.common;

import com.cannolicatfish.rankine.init.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.world.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event;

public class SaplingGrowTreeHandler {
    public static void onSaplingGrow(SaplingGrowTreeEvent event) {
        if (event.getRand().nextFloat() < 1- Config.GENERAL.SAPLING_GROW.get()) {
            event.setResult(Event.Result.DENY);
        }
        BlockPos pos = event.getPos();
        LevelAccessor worldIn = event.getWorld();
        if (worldIn.getBlockState(pos).is(Blocks.SPRUCE_SAPLING)) {

        }
    }
}
