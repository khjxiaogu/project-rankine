package com.cannolicatfish.rankine.blocks.asphalt;

import com.cannolicatfish.rankine.blocks.states.AsphaltStates;
import com.cannolicatfish.rankine.init.RankineItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BaseAsphaltBlock extends HorizontalBlock {
    public static final EnumProperty<AsphaltStates> LINE_TYPE = EnumProperty.create("line_type", AsphaltStates.class);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;

    public BaseAsphaltBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE,0).with(LINE_TYPE, AsphaltStates.NONE).with(HORIZONTAL_FACING, Direction.NORTH));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE,LINE_TYPE,HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(AGE,0).with(LINE_TYPE, AsphaltStates.NONE).with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return state.get(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.01) {
            worldIn.setBlockState(pos, state.with(AGE, Math.min(state.get(AGE)+1,3)));
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        AsphaltStates TYPE = state.get(LINE_TYPE);
        Direction dir = player.getHorizontalFacing();
        if (Tags.Items.DYES_WHITE.contains(player.getHeldItem(handIn).getItem())) {
            if (TYPE.equals(AsphaltStates.WHITE_LINE)) {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.WHITE_DASHED_LINE), 2);
            } else if (TYPE.equals(AsphaltStates.WHITE_DASHED_LINE)) {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.CROSSWALK), 2);
            } else if (TYPE.equals(AsphaltStates.CROSSWALK)) {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.NONE), 2);
            } else {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.WHITE_LINE), 2);
            }
            return ActionResultType.SUCCESS;
        } else if (Tags.Items.DYES_YELLOW.contains(player.getHeldItem(handIn).getItem())) {
            if (TYPE.equals(AsphaltStates.YELLOW_DOUBLE_LINE)) {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.YELLOW_LINE), 2);
            } else if (TYPE.equals(AsphaltStates.YELLOW_LINE)) {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.YELLOW_DASHED_LINE), 2);
            } else if (TYPE.equals(AsphaltStates.YELLOW_DASHED_LINE)) {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.YELLOW_DASHED_DOUBLE_LINE), 2);
            } else if (TYPE.equals(AsphaltStates.YELLOW_DASHED_DOUBLE_LINE)) {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.NONE), 2);
            } else {
                worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.YELLOW_DOUBLE_LINE), 2);
            }
            return ActionResultType.SUCCESS;
        } else if (player.getHeldItem(handIn).getItem().equals(RankineItems.CUPRONICKEL_TRAPDOOR.get())) {
            worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(LINE_TYPE, AsphaltStates.MANHOLE), 2);
            return ActionResultType.SUCCESS;
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }





}
