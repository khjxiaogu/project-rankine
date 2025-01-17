package com.cannolicatfish.rankine.data;

import com.cannolicatfish.rankine.ProjectRankine;
import com.cannolicatfish.rankine.init.RankineFluids;
import com.cannolicatfish.rankine.init.RankineTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class RankineFluidTagsProvider extends FluidTagsProvider {

    public RankineFluidTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, ProjectRankine.MODID, existingFileHelper);
    }

    public String getName() {
        return "Project Rankine - Fluid Tags";
    }

    @Override
    protected void addTags() {
        this.tag(RankineTags.Fluids.LATEX).add(RankineFluids.LATEX, RankineFluids.FLOWING_LATEX);
        this.tag(RankineTags.Fluids.RESIN).add(RankineFluids.RESIN, RankineFluids.FLOWING_RESIN);
        this.tag(RankineTags.Fluids.SAP).add(RankineFluids.SAP, RankineFluids.FLOWING_SAP);
        this.tag(RankineTags.Fluids.MAPLE_SAP).add(RankineFluids.MAPLE_SAP, RankineFluids.FLOWING_MAPLE_SAP);
        this.tag(RankineTags.Fluids.JUGLONE).add(RankineFluids.JUGLONE, RankineFluids.FLOWING_JUGLONE);
        this.tag(RankineTags.Fluids.SULFURIC_ACID).add(RankineFluids.SULFURIC_ACID, RankineFluids.FLOWING_SULFURIC_ACID);

    }


}
