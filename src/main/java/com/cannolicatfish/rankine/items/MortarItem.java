package com.cannolicatfish.rankine.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class MortarItem extends Item {
    public MortarItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World worldIn = context.getWorld();
        Block block = worldIn.getBlockState(context.getPos()).getBlock();
        ResourceLocation rs = block.getRegistryName();
            if (rs != null) {
                ResourceLocation rs2 = new ResourceLocation(rs.getNamespace(), rs.getPath()+"_bricks");
                if (ForgeRegistries.BLOCKS.containsKey(rs2)) {
                    worldIn.setBlockState(context.getPos(), Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(rs2)).getDefaultState(), 2);
                    spawnParticles(worldIn, context.getPos(), 0);
                    context.getItem().shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }
        return super.onItemUse(context);
    }

    @OnlyIn(Dist.CLIENT)
    public static void spawnParticles(IWorld worldIn, BlockPos posIn, int data) {
        if (data == 0) {
            data = 15;
        }

        BlockState blockstate = worldIn.getBlockState(posIn);
        if (!blockstate.isAir(worldIn, posIn)) {
            double d0 = 0.5D;
            double d1;
            if (blockstate.isIn(Blocks.WATER)) {
                data *= 3;
                d1 = 1.0D;
                d0 = 3.0D;
            } else if (blockstate.isOpaqueCube(worldIn, posIn)) {
                posIn = posIn.up();
                data *= 3;
                d0 = 3.0D;
                d1 = 1.0D;
            } else {
                d1 = blockstate.getShape(worldIn, posIn).getEnd(Direction.Axis.Y);
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
                if (!worldIn.getBlockState((new BlockPos(d6, d7, d8)).down()).isAir()) {
                    worldIn.addParticle(ParticleTypes.WHITE_ASH, d6, d7, d8, d2, d3, d4);
                }
            }

        }
    }
}
