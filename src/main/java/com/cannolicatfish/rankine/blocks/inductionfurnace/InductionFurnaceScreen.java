package com.cannolicatfish.rankine.blocks.inductionfurnace;

import com.cannolicatfish.rankine.ProjectRankine;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class InductionFurnaceScreen extends ContainerScreen<InductionFurnaceContainer> {
    private ResourceLocation GUI = new ResourceLocation(ProjectRankine.MODID, "textures/gui/induction_furnace.png");
    public InductionFurnaceScreen(InductionFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        GlStateManager._color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(this.GUI);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(p_230450_1_, i, j, 0, 0, this.imageWidth, this.imageHeight);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(p_230450_1_, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        if(this.menu.isBurning())
        {

            int k = this.menu.getBurnLeftScaled(13);
            this.blit(p_230450_1_, this.leftPos + 10, this.topPos + 22 + 12 - k, 176, 12 - k, 14, k + 1);

        }

        int l = this.menu.getCookProgressScaled(24);
        this.blit(p_230450_1_, this.leftPos + 98, this.topPos + 32, 176, 14, l + 1, 16);
    }

    @Override
    protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
        drawCenteredString(p_230451_1_, Minecraft.getInstance().font, "Induction Furnace", 92, 10, 0xffffff);
        int ymod = 0;
        for (String s : this.menu.getOutputString().getKey()) {
            drawCenteredString(p_230451_1_, Minecraft.getInstance().font, s, 92, 60 + ymod, this.menu.getOutputString().getValue());
            ymod += 10;
        }

    }



}
