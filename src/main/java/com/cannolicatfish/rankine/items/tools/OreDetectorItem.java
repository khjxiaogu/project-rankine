package com.cannolicatfish.rankine.items.tools;

import com.cannolicatfish.rankine.init.Config;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

import java.util.Optional;

import net.minecraft.world.item.Item.Properties;

public class OreDetectorItem extends Item {

    public OreDetectorItem(Properties properties) {
        super(properties);
    }

    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level worldIn = context.getLevel();
        BlockPos pos = context.getClickedPos();

        Optional<BlockPos> b = BlockPos.findClosestMatch(pos, Config.TOOLS.ORE_DETECTOR_RANGE.get(), Config.TOOLS.ORE_DETECTOR_RANGE.get(), (p) -> worldIn.getBlockState(p).is(Tags.Blocks.ORES));
        if (player != null && b.isPresent()) {
            worldIn.playSound(player,pos, SoundEvents.NOTE_BLOCK_BELL, SoundSource.PLAYERS,1.0F, worldIn.getRandom().nextFloat() * 0.4F + 0.8F);

            if (!worldIn.isClientSide()) {
                BlockState ORE = worldIn.getBlockState(b.get());
                player.displayClientMessage(new TranslatableComponent("item.rankine.ore_detector.message", ORE.getBlock().getName(), "UNKNOWN", b.get().getX(), b.get().getY(), b.get().getZ()), false);

                context.getItemInHand().hurtAndBreak(1, player, (p) -> {
                    p.broadcastBreakEvent(context.getHand());
                });
            }
        }
        return InteractionResult.SUCCESS;
    }
}
