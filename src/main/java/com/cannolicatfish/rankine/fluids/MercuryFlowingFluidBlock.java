package com.cannolicatfish.rankine.fluids;

import com.cannolicatfish.rankine.potion.RankineEffects;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock.Properties;

public class MercuryFlowingFluidBlock extends RankineFlowingFluidBlock{
    public MercuryFlowingFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            LivingEntity ent = (LivingEntity) entityIn;
            boolean flag = (entityIn instanceof PlayerEntity && ((PlayerEntity) entityIn).isCreative());
            if (!flag) {
                EffectInstance cur = ent.getEffect(RankineEffects.MERCURY_POISONING);
                ent.addEffect(new EffectInstance(RankineEffects.MERCURY_POISONING, Math.min(1600,cur == null ? 5 : cur.getDuration() + 5), 0, false, false, true));
            }
        }
        super.entityInside(state, worldIn, pos, entityIn);
    }
}
