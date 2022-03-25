package com.cannolicatfish.rankine.potion;

import com.cannolicatfish.rankine.ProjectRankine;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class RankinePotions {
    public static final Potion MERCURY_POISON = new Potion(new MobEffectInstance(RankineEffects.MERCURY_POISONING,800)).setRegistryName(ProjectRankine.MODID,"mercury_poison");
    public static final Potion CONDUCTIVE_POTION = new Potion(new MobEffectInstance(RankineEffects.CONDUCTIVE,500)).setRegistryName(ProjectRankine.MODID,"conductive_potion");
}
