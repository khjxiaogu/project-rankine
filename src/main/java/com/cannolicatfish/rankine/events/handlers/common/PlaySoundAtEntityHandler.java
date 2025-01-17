package com.cannolicatfish.rankine.events.handlers.common;

import com.cannolicatfish.rankine.blocks.gases.PitchModulating;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

public class PlaySoundAtEntityHandler {

    public static void onPlaySound(PlaySoundAtEntityEvent event) {
        if (event.getEntity() != null) {
            BlockPos headPosition = new BlockPos(event.getEntity().getEyePosition());
            if (event.getEntity().getLevel().getBlockState(headPosition).getBlock() instanceof PitchModulating) {
                float pitchMult = ((PitchModulating) event.getEntity().getLevel().getBlockState(headPosition).getBlock()).getPitchMultiplier();
                event.setPitch(Mth.clamp(event.getDefaultPitch() * pitchMult,0,2));
            }
        }
    }
}
