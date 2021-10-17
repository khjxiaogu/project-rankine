package com.cannolicatfish.rankine.blocks.buildingmodes;

import com.cannolicatfish.rankine.init.RankineItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;

import javax.annotation.Nullable;

public class RankinePlanksBlock extends Block {
    public static final EnumProperty<PlanksBuildingStates> PLANKS_TYPE = EnumProperty.create("planks_type", PlanksBuildingStates.class);

    public RankinePlanksBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(PLANKS_TYPE, PlanksBuildingStates.NORMAL));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Item heldItem = context.getPlayer().getHeldItemOffhand().getItem();
        if (heldItem == RankineItems.BUILDING_TOOL.get()) {
            return this.getDefaultState().with(PLANKS_TYPE, PlanksBuildingStates.VERTICAL);
        } else {
            return this.getDefaultState().with(PLANKS_TYPE, PlanksBuildingStates.NORMAL);
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PLANKS_TYPE);
    }


}