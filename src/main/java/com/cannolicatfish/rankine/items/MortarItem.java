package com.cannolicatfish.rankine.items;

import com.cannolicatfish.rankine.blocks.RankineVerticalSlabBlock;
import com.cannolicatfish.rankine.init.RankineBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import net.minecraft.item.Item.Properties;

public class MortarItem extends Item {
    public MortarItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World worldIn = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();
        ResourceLocation rs = block.getRegistryName();
            if (rs != null) {
                ResourceLocation rs2 = new ResourceLocation(rs.getNamespace(), rs.getPath()+"_bricks");
                if (ForgeRegistries.BLOCKS.containsKey(rs2) && !worldIn.isClientSide()) {
                    worldIn.setBlock(pos, Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(rs2)).defaultBlockState(), 2);
                    spawnParticles(worldIn, pos, 0);
                    context.getItemInHand().shrink(1);
                    return ActionResultType.SUCCESS;
                } else if (block == RankineBlocks.CAST_IRON_SUPPORT.get() && !worldIn.isClientSide()) {
                    int i = 0;
                    while (worldIn.getBlockState(pos.above(i)) == RankineBlocks.CAST_IRON_SUPPORT.get().defaultBlockState()) {
                        worldIn.setBlockAndUpdate(pos.above(i), RankineBlocks.CONCRETE.get().defaultBlockState());
                        ++i;
                        if (context.getItemInHand().getCount() >= 1) {
                            context.getItemInHand().shrink(1);
                        } else {
                            break;
                        }
                    }
                    i = 1;
                    while (worldIn.getBlockState(pos.below(i)) == RankineBlocks.CAST_IRON_SUPPORT.get().defaultBlockState()) {
                        worldIn.setBlockAndUpdate(pos.below(i), RankineBlocks.CONCRETE.get().defaultBlockState());
                        ++i;
                        if (context.getItemInHand().getCount() >= 1) {
                            context.getItemInHand().shrink(1);
                        } else {
                            break;
                        }
                    }
                    return ActionResultType.SUCCESS;
                } else if (block == RankineBlocks.CAST_IRON_SUPPORT_SLAB.get() && !worldIn.isClientSide()) {
                    worldIn.setBlockAndUpdate(pos, RankineBlocks.CONCRETE_SLAB.get().defaultBlockState().setValue(BlockStateProperties.SLAB_TYPE, state.getValue(BlockStateProperties.SLAB_TYPE)).setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)));
                    context.getItemInHand().shrink(1);
                    return ActionResultType.SUCCESS;
                } else if (block == RankineBlocks.CAST_IRON_SUPPORT_STAIRS.get() && !worldIn.isClientSide()) {
                    worldIn.setBlockAndUpdate(pos, RankineBlocks.CONCRETE_STAIRS.get().defaultBlockState().setValue(StairsBlock.SHAPE, state.getValue(StairsBlock.SHAPE)).setValue(StairsBlock.FACING, state.getValue(StairsBlock.FACING)).setValue(StairsBlock.HALF, state.getValue(StairsBlock.HALF)).setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)));
                    context.getItemInHand().shrink(1);
                    return ActionResultType.SUCCESS;
                } else if (block == RankineBlocks.CONCRETE_VERTICAL_SLAB.get() && !worldIn.isClientSide()) {
                    worldIn.setBlockAndUpdate(pos, RankineBlocks.CONCRETE_VERTICAL_SLAB.get().defaultBlockState().setValue(RankineVerticalSlabBlock.HORIZONTAL_FACING, state.getValue(RankineVerticalSlabBlock.HORIZONTAL_FACING)).setValue(RankineVerticalSlabBlock.TYPE, state.getValue(RankineVerticalSlabBlock.TYPE)));
                    context.getItemInHand().shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }
            return super.useOn(context);
        }

    public static void spawnParticles(IWorld worldIn, BlockPos posIn, int data) {
        if (worldIn.isClientSide())
        {
            if (data == 0) {
                data = 15;
            }

            BlockState blockstate = worldIn.getBlockState(posIn);
            if (!blockstate.isAir(worldIn, posIn)) {
                double d0 = 0.5D;
                double d1;
                if (blockstate.is(Blocks.WATER)) {
                    data *= 3;
                    d1 = 1.0D;
                    d0 = 3.0D;
                } else if (blockstate.isSolidRender(worldIn, posIn)) {
                    posIn = posIn.above();
                    data *= 3;
                    d0 = 3.0D;
                    d1 = 1.0D;
                } else {
                    d1 = blockstate.getShape(worldIn, posIn).max(Direction.Axis.Y);
                }

                worldIn.addParticle(ParticleTypes.WHITE_ASH, (double)posIn.getX() + 0.5D, (double)posIn.getY() + 0.5D, (double)posIn.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);

                for(int i = 0; i < data; ++i) {
                    double d2 = random.nextGaussian() * 0.02D;
                    double d3 = random.nextGaussian() * 0.02D;
                    double d4 = random.nextGaussian() * 0.02D;
                    double d5 = 0.5D - d0;
                    double d6 = (double)posIn.getX() + d5 + random.nextDouble() * d0 * 2.0D;
                    double d7 = (double)posIn.getY() + random.nextDouble() * d1;
                    double d8 = (double)posIn.getZ() + d5 + random.nextDouble() * d0 * 2.0D;
                    if (!worldIn.getBlockState((new BlockPos(d6, d7, d8)).below()).isAir()) {
                        worldIn.addParticle(ParticleTypes.WHITE_ASH, d6, d7, d8, d2, d3, d4);
                    }
                }

            }
        }

    }

    /*
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            tooltip.add(new StringTextComponent("Used for creating bricks.").mergeStyle(TextFormatting.GRAY));
    }

     */


}
