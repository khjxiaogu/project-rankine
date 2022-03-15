package com.cannolicatfish.rankine.blocks.charcoalpit;

import com.cannolicatfish.rankine.blocks.RankineEightLayerBlock;
import com.cannolicatfish.rankine.init.Config;
import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.init.VanillaIntegration;
import com.cannolicatfish.rankine.util.WorldgenUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static com.cannolicatfish.rankine.init.RankineBlocks.CHARCOAL_PIT_TILE;

public class CharcoalPitTile extends TileEntity implements ITickableTileEntity {
    int MAX_HEIGHT = Config.MACHINES.CHARCOAL_PIT_HEIGHT.get();
    double RADIUS = Config.MACHINES.CHARCOAL_PIT_RADIUS.get()+0.5;
    int totalTime;
    int proccessTime = 0;

    public CharcoalPitTile() {
        super(CHARCOAL_PIT_TILE);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.proccessTime = nbt.getInt("ProcessTime");
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.putInt("ProcessTime", this.proccessTime);
        return compound;
    }

    public void tick() {
        if (!level.isAreaLoaded(worldPosition, 1) || !this.getBlockState().getValue(CharcoalPitBlock.LIT) || level.getBlockState(worldPosition.below()).is(RankineBlocks.CHARCOAL_PIT.get())) return;
        if (proccessTime == 0) {
            totalTime = MathHelper.nextInt(level.random,(int) Math.round(0.8*Config.MACHINES.CHARCOAL_PIT_SPEED.get()),(int) Math.round(1.2*Config.MACHINES.CHARCOAL_PIT_SPEED.get()));
        }

        if (proccessTime >= totalTime) {
            Set<BlockPos> checkedBlocks = new HashSet<>();
            Stack<BlockPos> toCheck = new Stack<>();
            Set<BlockPos> validLogs = new HashSet<>();
            Set<BlockPos> invalidLogs = new HashSet<>();
            Set<BlockPos> toChar = new HashSet<>();

            toCheck.add(worldPosition);
            validLogs.add(worldPosition);
            while (!toCheck.isEmpty()) {
                BlockPos cp = toCheck.pop();
                if (!checkedBlocks.contains(cp)) {
                    checkedBlocks.add(cp);
                    for (Direction dir : Direction.values()) {
                        if (cp.getY() - worldPosition.getY() <= MAX_HEIGHT && cp.getY() - worldPosition.getY() >= 0 && Math.pow(worldPosition.getX() - cp.getX(), 2) + Math.pow(worldPosition.getZ() - cp.getZ(), 2) <= RADIUS * RADIUS + 0.5) {
                            BlockState target = level.getBlockState(cp.relative(dir));
                            if (target.is(BlockTags.LOGS_THAT_BURN) || target.is(RankineBlocks.CHARCOAL_PIT.get())) {
                                boolean valid = true;
                                for (Direction dir2 : Direction.values()) {
                                    if (dir.getOpposite() == dir2) continue;
                                    if (!level.getBlockState(cp.relative(dir).relative(dir2)).isSolidRender(level, cp.relative(dir).relative(dir2))) {
                                        valid = false;
                                        invalidLogs.add(cp.relative(dir));
                                        break;
                                    }
                                }
                                if (valid) {
                                    toCheck.add(cp.relative(dir));
                                    validLogs.add(cp.relative(dir));
                                }
                            }
                        }
                    }

                }
            }


            int k = 0;
            while (level.getBlockState(worldPosition.above(k)).is(RankineBlocks.CHARCOAL_PIT.get()) && k <= MAX_HEIGHT) {
                k++;
            }

            for (int i = 0; i<=k; ++i) {
                for (BlockPos b : BlockPos.betweenClosed(worldPosition.offset(-RADIUS,i,-RADIUS),worldPosition.offset(RADIUS,i,RADIUS))) {
                    if (validLogs.contains(b.immutable())) {
                        int layerCount = logLayerCount(level,level.getBlockState(b).getBlock());
                        if (layerCount == 0) continue;
                        int j = 1;
                        while (WorldgenUtils.isGasOrAir(level,b.below(j)) && j <= i) {
                            j++;
                        }
                        if (level.getBlockState(b.below(j)).is(RankineBlocks.CHARCOAL_BLOCK.get())) {
                            int downState = level.getBlockState(b.below(j)).getValue(RankineEightLayerBlock.LAYERS);
                            if (downState + layerCount > 8) {
                                level.setBlock(b.below(j), RankineBlocks.CHARCOAL_BLOCK.get().defaultBlockState().setValue(RankineEightLayerBlock.LAYERS, 8),3);
                                level.setBlock(b.below(j-1), RankineBlocks.CHARCOAL_BLOCK.get().defaultBlockState().setValue(RankineEightLayerBlock.LAYERS, downState+layerCount-8),3);
                                if (j>1) {
                                    level.removeBlock(b,false);
                                }
                            } else {
                                level.setBlock(b.below(j), RankineBlocks.CHARCOAL_BLOCK.get().defaultBlockState().setValue(RankineEightLayerBlock.LAYERS, downState+layerCount),3);
                                level.removeBlock(b,false);
                            }
                        } else {
                            level.setBlock(b, RankineBlocks.CHARCOAL_BLOCK.get().defaultBlockState().setValue(RankineEightLayerBlock.LAYERS, layerCount),3);
                        }
                    } else if (invalidLogs.contains(b.immutable())) {
                        level.setBlock(b, RankineBlocks.CARBON_DIOXIDE_GAS_BLOCK.get().defaultBlockState(),3);
                    }
                }
            }
        } else {
            proccessTime += 1;
        }
    }

    public static int logLayerCount(World worldIn, Block target) {
        if (VanillaIntegration.fuelValueMap.containsKey(target.asItem())) {
            int value = VanillaIntegration.fuelValueMap.get(target.asItem());
            return (int) Math.floor(MathHelper.clamp(value/100D -2.0D + (worldIn.random.nextFloat() < (value%100)/100D ? 1 : 0),1,8));
        } else if (target.is(BlockTags.LOGS_THAT_BURN)) {
            return 2;
        } else if (target.is(RankineBlocks.CHARCOAL_PIT.get())) {
            return 2;
        }
        return 0;
    }

}
