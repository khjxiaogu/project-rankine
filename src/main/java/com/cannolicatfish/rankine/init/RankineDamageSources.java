package com.cannolicatfish.rankine.init;

import net.minecraft.world.damagesource.DamageSource;

public class RankineDamageSources {

    public static final DamageSource COLUMNS = (new DamageSource("columns"));
    public static final DamageSource CANNONBALL = (new DamageSource("cannonball"));
    public static final DamageSource SUFFOCATION = (new DamageSource("suffocating")).bypassArmor().bypassMagic();
}
