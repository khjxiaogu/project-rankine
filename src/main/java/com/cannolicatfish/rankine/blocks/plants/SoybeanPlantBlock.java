package com.cannolicatfish.rankine.blocks.plants;

import com.cannolicatfish.rankine.init.RankineItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import net.minecraft.block.AbstractBlock.Properties;

public class SoybeanPlantBlock extends RankineCropsBlock {

    public SoybeanPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return RankineItems.SOYBEANS.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.is(Blocks.FARMLAND);
    }
}