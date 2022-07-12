package com.cannolicatfish.rankine.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class SiltBlock extends SandBlock {

    public SiltBlock(int dustColorIn, Properties properties) {
        super(dustColorIn, properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return plantable.getPlantType(world, pos.relative(facing)).equals(PlantType.DESERT) || plantable.getPlantType(world, pos.relative(facing)).equals(PlantType.BEACH);
    }
}