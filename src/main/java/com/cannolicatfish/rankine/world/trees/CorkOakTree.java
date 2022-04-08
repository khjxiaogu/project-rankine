package com.cannolicatfish.rankine.world.trees;

import com.cannolicatfish.rankine.world.gen.RankineConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;

import javax.annotation.Nullable;
import java.util.Random;

public class CorkOakTree extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
        return Holder.direct(RankineConfiguredFeatures.CORK_OAK_TREE);
    }
}
