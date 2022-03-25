package com.cannolicatfish.rankine.blocks.asphalt;

import com.cannolicatfish.rankine.init.RankineLists;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

public class BlueAsphaltBlock extends BaseAsphaltBlock {

    public BlueAsphaltBlock() {
        super();
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        Direction dir = player.getDirection();
        if (Tags.Items.DYES.contains(player.getItemInHand(handIn).getItem())) {
            if (state.getValue(FACING) != dir) {
                worldIn.setBlock(pos, state.setValue(FACING, dir), 3);
            } else {
                worldIn.setBlock(pos, RankineLists.BLUE_ASPHALT_BLOCKS.get((RankineLists.BLUE_ASPHALT_BLOCKS.indexOf(state.getBlock())+1)%RankineLists.BLUE_ASPHALT_BLOCKS.size()).defaultBlockState().setValue(SIZE,state.getValue(SIZE)).setValue(FACING, dir), 3);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}
