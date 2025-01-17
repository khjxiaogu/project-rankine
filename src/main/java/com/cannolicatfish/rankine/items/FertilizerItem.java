package com.cannolicatfish.rankine.items;

import com.cannolicatfish.rankine.blocks.GrassySoilBlock;
import com.cannolicatfish.rankine.init.Config;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import net.minecraft.world.item.Item.Properties;

public class FertilizerItem extends Item {
    public FertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level worldIn = context.getLevel();
        BlockPos pos = context.getClickedPos();

        int radius = Config.GENERAL.HERBICIDE_RANGE.get();

        if (!worldIn.isClientSide) {
            for (BlockPos b : BlockPos.betweenClosed(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius))) {
                Block blk = worldIn.getBlockState(b).getBlock();
                if (blk instanceof GrassySoilBlock && b.distSqr(pos) <= radius*radius) {
                    worldIn.setBlock(b, blk.defaultBlockState().setValue(GrassySoilBlock.DEAD, false), 2);
                }
            }
        }
        worldIn.playSound(null,pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,0.5f,1.2f);
        context.getItemInHand().shrink(1);
        return InteractionResult.SUCCESS;

    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            tooltip.add(new TextComponent("Restores vegetation").withStyle(ChatFormatting.GRAY));
    }


}
