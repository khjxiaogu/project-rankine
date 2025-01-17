package com.cannolicatfish.rankine.client.renders;

import com.cannolicatfish.rankine.entities.BalloonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import com.cannolicatfish.rankine.entities.BalloonEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class BalloonRenderer extends EntityRenderer<BalloonEntity> {
    public BalloonRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    public void render(BalloonEntity p_114634_, float p_114635_, float p_114636_, PoseStack p_114637_, MultiBufferSource p_114638_, int p_114639_) {
        BlockState blockstate = p_114634_.getBlockState();
        if (blockstate.getRenderShape() == RenderShape.MODEL) {
            Level level = p_114634_.getLevel();
            if (blockstate != level.getBlockState(p_114634_.blockPosition()) && blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                p_114637_.pushPose();
                BlockPos blockpos = new BlockPos(p_114634_.getX(), p_114634_.getBoundingBox().maxY, p_114634_.getZ());
                p_114637_.translate(-0.5D, 0.0D, -0.5D);
                BlockRenderDispatcher blockrenderdispatcher = Minecraft.getInstance().getBlockRenderer();
                for (net.minecraft.client.renderer.RenderType type : net.minecraft.client.renderer.RenderType.chunkBufferLayers()) {
                    if (ItemBlockRenderTypes.canRenderInLayer(blockstate, type)) {
                        net.minecraftforge.client.ForgeHooksClient.setRenderType(type);
                        blockrenderdispatcher.getModelRenderer().tesselateBlock(level, blockrenderdispatcher.getBlockModel(blockstate), blockstate, blockpos, p_114637_, p_114638_.getBuffer(type), false, new Random(), blockstate.getSeed(p_114634_.getStartPos()), OverlayTexture.NO_OVERLAY);
                    }
                }
                net.minecraftforge.client.ForgeHooksClient.setRenderType(null);
                p_114637_.popPose();
                super.render(p_114634_, p_114635_, p_114636_, p_114637_, p_114638_, p_114639_);
            }
        }
    }

    public ResourceLocation getTextureLocation(BalloonEntity p_114632_) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
