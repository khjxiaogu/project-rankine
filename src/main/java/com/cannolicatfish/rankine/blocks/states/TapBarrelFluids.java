package com.cannolicatfish.rankine.blocks.states;

import net.minecraft.util.StringRepresentable;

public enum TapBarrelFluids implements StringRepresentable {
    WATER("water"),
    LAVA("lava"),
    SAP("sap"),
    MAPLE_SAP("maple_sap"),
    RESIN("resin"),
    LATEX("latex"),
    JUGLONE("juglone");

    private final String name;

    TapBarrelFluids(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}