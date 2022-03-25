package com.cannolicatfish.rankine.items.indexer;

import com.cannolicatfish.rankine.ProjectRankine;
import com.cannolicatfish.rankine.recipe.ElementRecipe;
import com.cannolicatfish.rankine.util.PeriodicTableUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.Util;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class ElementIndexerScreen extends AbstractContainerScreen<ElementIndexerContainer> {
    private int currentScroll = 100;
    private ElementRecipe element = null;
    private static final PeriodicTableUtils utils = new PeriodicTableUtils();
    private ResourceLocation GUI = new ResourceLocation(ProjectRankine.MODID, "textures/gui/element_indexer.png");
    public ElementIndexerScreen(ElementIndexerContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.imageWidth = 176;
        this.imageHeight = 236;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }


    @Override
    protected void renderLabels(PoseStack matrixStack, int x, int y) {

        drawString(matrixStack,Minecraft.getInstance().font,this.currentScroll + "%",140,10,0xffffff);
        DecimalFormat df = Util.make(new DecimalFormat("##.#"), (p_234699_0_) -> {
            p_234699_0_.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
        });

        if (Minecraft.getInstance().level != null && element != this.menu.getSlotItem(Minecraft.getInstance().level))
        {
            element = this.menu.getSlotItem(Minecraft.getInstance().level);
        }
        if (element != null)
        {
            int durability = element.getDurability(this.currentScroll);
            int harvest = element.getMiningLevel(this.currentScroll);
            float mspeed = element.getMiningSpeed(this.currentScroll);
            float damage = element.getDamage(this.currentScroll);
            float attspeed = element.getAttackSpeed(this.currentScroll);
            int enchant = element.getEnchantability(this.currentScroll);
            float corr = element.getCorrosionResistance(this.currentScroll);
            float heat = element.getHeatResistance(this.currentScroll);
            float tough = element.getToughness(this.currentScroll);
            float elec = element.getElectrodePotential();
            drawString(matrixStack,Minecraft.getInstance().font,"Durability: " + durability,12,30,(durability > 0 ? 0x55FF55 : durability < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"Harvest Level: "+ harvest,12,42,(harvest > 0 ? 0x55FF55 : harvest < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"Mining Speed: "+ df.format(mspeed),12,54,(mspeed > 0 ? 0x55FF55 : mspeed < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"Damage: "+ df.format(damage),12,66,(damage > 0 ? 0x55FF55 : damage < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"Attack Speed: "+ df.format(attspeed),12,78,(attspeed > 0 ? 0x55FF55 : attspeed < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"Enchantability: "+ enchant,12,90,(enchant > 0 ? 0x55FF55 : enchant < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"Corrosion Resistance: "+ df.format(corr * 100) + "%",12,102,(corr > 0 ? 0x55FF55 : corr < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"Heat Resistance: "+ df.format(heat * 100) + "%",12,114,(heat > 0 ? 0x55FF55 : heat < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"Toughness: "+ df.format(tough * 100) + "%",12,126,(tough > 0 ? 0x55FF55 : tough < 0 ? 0xFF5555 : 0xffffff));
            drawString(matrixStack,Minecraft.getInstance().font,"E: "+ elec +"V",110,126,0x55FFFF);

            /*Enchantment e = element.getToolEnchantment(this.currentScroll);
            if (e != null) {
                drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer,e.getDisplayName(1).getString(),125,66,0x55FF55);
            }*/

            drawString(matrixStack,Minecraft.getInstance().font,element.getName().toUpperCase(Locale.ROOT),32,10,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,String.valueOf(element.getAtomicNumber()),138,32,0xffffff);
            drawScaledString(matrixStack,Minecraft.getInstance().font,String.valueOf(element.getSymbol()),138,42, 2,0xffffff);
        } else
        {
            //drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer,"MATERIALNAME",88,12,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Durability:",12,30,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Harvest Level:",12,42,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Mining Speed:",12,54,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Damage:",12,66,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Attack Speed:",12,78,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Enchantability:",12,90,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Corrosion Resistance:",12,102,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Heat Resistance:",12,114,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"Toughness:",12,126,0xffffff);
            drawString(matrixStack,Minecraft.getInstance().font,"E:",110,126,0x55FFFF);
        }

    }

    public void drawScaledString(PoseStack stack, Font fontRendererIn, String text, int x, int y, float size, int color) {
        GL11.glScalef(size,size,size);
        float mSize = (float)Math.pow(size,-1);
        drawString(stack, fontRendererIn,text,Math.round(x / size),Math.round(y / size),color);
        GL11.glScalef(mSize,mSize,mSize);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindForSetup(this.GUI);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack,relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(matrixStack, this.leftPos + 48, this.topPos + 22, 0, 254, this.currentScroll + 1, 2);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        this.currentScroll = this.currentScroll + (int) (delta);
        this.currentScroll = Mth.clamp(this.currentScroll, 1, 100);
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
