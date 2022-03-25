package com.cannolicatfish.rankine.blocks.states;

import net.minecraft.util.StringRepresentable;

public enum TilledSoilTypes implements StringRepresentable {
    DIRT("dirt"),
    SOUL_SOIL("soul_soil"),
    LOAM("loam"),
    HUMUS("humus"),
    CLAY_LOAM("clay_loam"),
    SANDY_LOAM("sandy_loam"),
    SILTY_LOAM("silty_loam"),
    SILTY_CLAY("silty_clay"),
    SANDY_CLAY("sandy_clay"),
    LOAMY_SAND("loamy_sand"),
    SILTY_CLAY_LOAM("silty_clay_loam"),
    SANDY_CLAY_LOAM("sandy_clay_loam");

    private final String name;

    TilledSoilTypes(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}