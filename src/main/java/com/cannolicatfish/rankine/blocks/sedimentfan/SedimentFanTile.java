package com.cannolicatfish.rankine.blocks.sedimentfan;

import com.cannolicatfish.rankine.init.Config;
import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.init.RankineRecipeTypes;
import com.cannolicatfish.rankine.init.RankineSoundEvents;
import com.cannolicatfish.rankine.recipe.RockGeneratorRecipe;
import com.cannolicatfish.rankine.util.RockGeneratorUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.cannolicatfish.rankine.init.RankineBlocks.SEDIMENT_FAN_TILE;

public class SedimentFanTile extends BlockEntity {

    public SedimentFanTile(BlockPos posIn, BlockState stateIn) {
        super(SEDIMENT_FAN_TILE, posIn, stateIn);
    }

    public void tick() {
        if (!level.isAreaLoaded(worldPosition, 1) || level.getGameTime() % 20 != 0) return;
        List<Fluid> adjPos = Arrays.asList(level.getFluidState(worldPosition.south().above()).getType(),level.getFluidState(worldPosition.north().above()).getType(), level.getFluidState(worldPosition.west().above()).getType(),level.getFluidState(worldPosition.east().above()).getType());
        if (level.getFluidState(worldPosition.above()).isSource()) {
            Direction dir = null;
            if (adjPos.contains(Fluids.FLOWING_WATER))
                switch (adjPos.indexOf(Fluids.FLOWING_WATER)) {
                    case 0:
                        dir = Direction.SOUTH;
                        break;
                    case 1:
                        dir = Direction.NORTH;
                        break;
                    case 2:
                        dir = Direction.WEST;
                        break;
                    case 3:
                        dir = Direction.EAST;
                        break;
                }

            if (dir != null) {
                List<Fluid> waterChecks = Arrays.asList(level.getFluidState(worldPosition.above().relative(dir, 2)).getType(), level.getFluidState(worldPosition.above().relative(dir, 3)).getType());
                if (waterChecks.stream().allMatch((e) -> e == Fluids.FLOWING_WATER)) {
                    Block sediment = level.getBlockState(worldPosition.relative(dir)).getBlock();
                    Block sediment2 = level.getBlockState(worldPosition.relative(dir, 2)).getBlock();
                    List<Block> adjPos2 = Arrays.asList(sediment,sediment2);
                    BlockPos end = worldPosition.above().relative(dir, 3);
                    ItemStack[] items = adjPos2.stream().map(ItemStack::new).toArray(ItemStack[]::new);
                    RockGeneratorRecipe recipe = level.getRecipeManager().getAllRecipesFor(RankineRecipeTypes.ROCK_GENERATOR).stream().flatMap((r) -> {
                        if (r.getGenType().equals(RockGeneratorUtils.RockGenType.SEDIMENTARY)) {
                            return Util.toStream(RankineRecipeTypes.ROCK_GENERATOR.tryMatch(r, level, new SimpleContainer(items)));
                        }
                        return null;
                    }).findFirst().orElse(null);
                    if (recipe != null) {
                        ItemStack output = recipe.getResultItem();
                        if (!output.isEmpty() && output.getItem() instanceof BlockItem) {
                            level.setBlock(end, ((BlockItem) output.getItem()).getBlock().defaultBlockState(), 19);
                            level.playSound(null,end, RankineSoundEvents.SEDIMENT_FAN_GEN.get(), SoundSource.BLOCKS,1.0f,1.0f);
                        }
                    } else {
                        level.setBlock(end, RankineBlocks.BRECCIA.get().defaultBlockState(), 19);
                        level.playSound(null, end, SoundEvents.SAND_HIT, SoundSource.BLOCKS, 1.0f, 1.0f);
                    }
                    if (level.getRandom().nextFloat() < Config.GENERAL.ROCK_GENERATOR_REMOVAL_CHANCE.get()) {
                        level.removeBlock(worldPosition.relative(dir,level.random.nextBoolean() ? 1 : 2),false);
                    }
                }
            }
        }
    }


    @OnlyIn(Dist.CLIENT)
    public static void spawnParticles(Level worldIn, BlockPos pos) {
        Random random = worldIn.getRandom();
        double d0 = (double)pos.getX() + random.nextDouble();
        double d1 = (double)pos.getY() - 0.05D;
        double d2 = (double)pos.getZ() + random.nextDouble();
        worldIn.addAlwaysVisibleParticle(ParticleTypes.WHITE_ASH, d0, d1, d2, 0.0D, 0.1D, 0.0D);
    }
}
