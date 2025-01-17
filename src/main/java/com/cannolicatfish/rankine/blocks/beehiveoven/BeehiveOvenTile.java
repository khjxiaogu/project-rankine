package com.cannolicatfish.rankine.blocks.beehiveoven;

import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.init.RankineRecipeTypes;
import com.cannolicatfish.rankine.recipe.BeehiveOvenRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Arrays;
import java.util.List;

import static com.cannolicatfish.rankine.init.RankineBlocks.BEEHIVE_OVEN_TILE;

public class BeehiveOvenTile extends BlockEntity {
    int cookingProgress;
    int cookingTotalTime;
    public BeehiveOvenTile(BlockPos posIn, BlockState stateIn) {
        super(BEEHIVE_OVEN_TILE, posIn, stateIn);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.cookingProgress = nbt.getInt("CookTime");
        this.cookingTotalTime = nbt.getInt("CookTimeTotal");
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("CookTime", this.cookingProgress);
        compound.putInt("CookTimeTotal", this.cookingTotalTime);
    }

    public static void tick(Level levelIn, BlockPos posIn, BlockState stateIn, BeehiveOvenTile tileIn) {
        if (!levelIn.isAreaLoaded(posIn, 1)) return;
        if (!stateIn.getValue(BeehiveOvenPitBlock.LIT)) return;
        float speedMod = structureCheck(levelIn, posIn);
        if (speedMod == 0.0f || cookTime(levelIn, posIn) == 0) {
            levelIn.setBlockAndUpdate(posIn, stateIn.setValue(BlockStateProperties.LIT, Boolean.FALSE));
            tileIn.cookingProgress = 0;
            return;
        }
        if (tileIn.cookingTotalTime == 0) tileIn.cookingTotalTime = Math.round(cookTime(levelIn, posIn) * speedMod);
        tileIn.cookingProgress += 1;
        if (tileIn.cookingProgress >= tileIn.cookingTotalTime) {
            int time = Math.round(cookTime(levelIn, posIn) * speedMod);
            if (time > tileIn.cookingTotalTime) {
                tileIn.cookingTotalTime += time - tileIn.cookingTotalTime;
                return;
            }

            for (BlockPos p: BlockPos.betweenClosed(posIn.offset(-1,1,-1),posIn.offset(1,2,1))) {
                Block target = levelIn.getBlockState(p).getBlock();
                if (target == Blocks.AIR) levelIn.setBlockAndUpdate(p, RankineBlocks.CARBON_DIOXIDE_GAS_BLOCK.get().defaultBlockState());
                BeehiveOvenRecipe recipe = levelIn.getRecipeManager().getRecipeFor(RankineRecipeTypes.BEEHIVE, new SimpleContainer(new ItemStack(target)), levelIn).orElse(null);
                if (recipe != null) {
                    ItemStack output = recipe.getResultItem();
                    if (!output.isEmpty() && output.getItem() instanceof BlockItem) {
                        levelIn.setBlockAndUpdate(p, ((BlockItem) output.getItem()).getBlock().defaultBlockState());
                    }
                }
            }
            levelIn.setBlockAndUpdate(posIn, stateIn.setValue(BlockStateProperties.LIT, Boolean.FALSE));
            tileIn.cookingProgress = 0;

        }
    }


    private static int cookTime(Level levelIn, BlockPos posIn) {
        int time = 0;
        for (BlockPos p: BlockPos.betweenClosed(posIn.offset(-1,1,-1),posIn.offset(1,2,1))) {
            Block target = levelIn.getBlockState(p).getBlock();
            if (target instanceof AirBlock) continue;
            BeehiveOvenRecipe recipe = levelIn.getRecipeManager().getRecipeFor(RankineRecipeTypes.BEEHIVE, new SimpleContainer(new ItemStack(target)), levelIn).orElse(null);
            if (recipe != null) {
                ItemStack output = recipe.getResultItem();
                if (!output.isEmpty() && output.getItem() instanceof BlockItem) {
                    time += levelIn.getRandom().nextInt(recipe.getMinCookTime(), recipe.getMaxCookTime());
                }
            }
        }
        return time;
    }

    private static float structureCheck(Level levelIn, BlockPos posIn) {
        for (int i = 1; i <= 6; i++) {
            if (!levelIn.isEmptyBlock(posIn.above(i))) {
                return 0.0f;
            }
        }

        float speedMod = 0.0f;
        for (BlockPos b : ovenStructure(posIn)) {
            if (levelIn.getBlockState(b).is(RankineBlocks.ULTRA_HIGH_REFRACTORY_BRICKS.get())) {
                speedMod = Math.max(speedMod, 0.25f);
            } else if (levelIn.getBlockState(b).is(RankineBlocks.HIGH_REFRACTORY_BRICKS.get())) {
                speedMod = Math.max(speedMod, 0.5f);
            } else if (levelIn.getBlockState(b).is(RankineBlocks.REFRACTORY_BRICKS.get())) {
                speedMod = 1.0f;
            } else {
                return 0.0f;
            }
        }
        return speedMod;
    }

    public static List<BlockPos> ovenStructure(BlockPos posIn) {
        return Arrays.asList(
                posIn.offset(-1,0,-1),
                posIn.offset(-1,0,0),
                posIn.offset(-1,0,1),
                posIn.offset(1,0,-1),
                posIn.offset(1,0,0),
                posIn.offset(1,0,1),
                posIn.offset(0,0,-1),
                posIn.offset(0,0,1),

                posIn.offset(-2,0,-1),
                posIn.offset(-2,0,0),
                posIn.offset(-2,0,1),
                posIn.offset(2,0,-1),
                posIn.offset(2,0,0),
                posIn.offset(2,0,1),
                posIn.offset(-1,0,-2),
                posIn.offset(0,0,-2),
                posIn.offset(1,0,-2),
                posIn.offset(-1,0,2),
                posIn.offset(0,0,2),
                posIn.offset(1,0,2),

                posIn.offset(-2,1,-1),
                posIn.offset(-2,1,1),
                posIn.offset(2,1,-1),
                posIn.offset(2,1,1),
                posIn.offset(-1,1,-2),
                posIn.offset(1,1,-2),
                posIn.offset(-1,1,2),
                posIn.offset(1,1,2),

                posIn.offset(-2,2,-1),
                posIn.offset(-2,2,1),
                posIn.offset(2,2,-1),
                posIn.offset(2,2,1),
                posIn.offset(-1,2,-2),
                posIn.offset(1,2,-2),
                posIn.offset(-1,2,2),
                posIn.offset(1,2,2),

                posIn.offset(-1,3,-1),
                posIn.offset(-2,3,0),
                posIn.offset(-1,3,1),
                posIn.offset(1,3,-1),
                posIn.offset(2,3,0),
                posIn.offset(1,3,1),
                posIn.offset(0,3,-2),
                posIn.offset(0,3,2),
                posIn.offset(0,3,-1),
                posIn.offset(0,3,1),
                posIn.offset(-1,3,0),
                posIn.offset(1,3,0)
        );
    }

}
