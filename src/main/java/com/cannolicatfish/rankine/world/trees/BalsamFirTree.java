package com.cannolicatfish.rankine.world.trees;

import com.cannolicatfish.rankine.init.RankineFeatures;
import com.cannolicatfish.rankine.world.gen.RankineBiomeFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class BalsamFirTree extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
        return RankineFeatures.BALSAM_FIR_TREE.get().configured(RankineBiomeFeatures.BALSAM_FIR_TREE_CONFIG);
    }
}
