package com.cannolicatfish.rankine.client.integration.jei.categories;

import com.cannolicatfish.rankine.ProjectRankine;
import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.init.RankineItems;
import com.cannolicatfish.rankine.recipe.MixingRecipe;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.List;

public class MixingRecipeCategory implements IRecipeCategory<MixingRecipe> {

    public static ResourceLocation UID = new ResourceLocation(ProjectRankine.MODID, "mixing");
    private final IDrawable background;
    private final String localizedName;
    private final IDrawable overlay;
    private final IDrawable icon;

    public MixingRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createBlankDrawable(185, 146);
        localizedName = I18n.get("rankine.jei.mixing");
        overlay = guiHelper.createDrawable(new ResourceLocation(ProjectRankine.MODID, "textures/gui/mixing_jei.png"),
                0, 15, 180, 141);
        icon = guiHelper.createDrawableIngredient(new ItemStack(RankineBlocks.MIXING_BARREL.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends MixingRecipe> getRecipeClass() {
        return MixingRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent(localizedName);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void draw(MixingRecipe recipe, PoseStack ms, double mouseX, double mouseY) {
        Font font = Minecraft.getInstance().font;
        //RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        overlay.draw(ms, 0, 4);
        RenderSystem.disableBlend();
        //RenderSystem.disableAlphaTest();

        float matScale = recipe.getMatScale();
        if (matScale < 1) {
            font.draw(ms,   Math.round(1/matScale) + ":" + 1, (float)151, 26, 0x000000);
        } else {
            font.draw(ms, 1 + ":" + Math.round(matScale), (float)151, 26, 0x000000);
        }

        font.draw(ms, String.valueOf(recipe.getMixTime()),  (float)151, 100, 0x7C3E3E);
    }

    @Override
    public void setIngredients(MixingRecipe recipe, IIngredients iIngredients) {
        ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
        for (Ingredient i : recipe.getIngredients()) {
            builder.add(Arrays.asList(i.getItems()));
        }
        iIngredients.setInput(VanillaTypes.FLUID,recipe.getFluidFilled());
        iIngredients.setInputLists(VanillaTypes.ITEM, builder.build());
        iIngredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MixingRecipe recipe, IIngredients ingredients) {
        int index = 0;
        int amtReq = (int) recipe.getRequired().stream().filter(aBoolean -> aBoolean).count();
        for (int i = 0; i < ingredients.getInputs(VanillaTypes.ITEM).size(); i++) {
            if (i < amtReq) {
                recipeLayout.getItemStacks().init(i, false, 14 + 18*i, 6);
            } else {
                int floor = Math.floorDiv(i - amtReq,10);
                recipeLayout.getItemStacks().init(i, true, ((i - amtReq) - (10*floor)) * 18 + 2, 40 + (16*floor));
            }

            if (!ingredients.getInputs(VanillaTypes.ITEM).get(i).contains(ItemStack.EMPTY) && ingredients.getInputs(VanillaTypes.ITEM).get(i).stream().noneMatch((s) -> s.getItem() == RankineItems.ELEMENT.get())) {
                recipeLayout.getItemStacks().set(i, ingredients.getInputs(VanillaTypes.ITEM).get(i));
            }

            index = i;
        }
        for (List<FluidStack> s : ingredients.getInputs(VanillaTypes.FLUID)) {
            if (s.size() > 0 && !s.get(0).isEmpty()) {
                recipeLayout.getFluidStacks().init(index,true,150,7);
                recipeLayout.getFluidStacks().set(index,s);
            }
            index++;
        }

        int endIndex = index;
        recipeLayout.getItemStacks().addTooltipCallback((i, b, stack, list) -> {
            if (i < endIndex) {
                list.add(new TextComponent("Min: " + Math.round(recipe.getMins().get(i) * 100) + "%"));
                list.add(new TextComponent("Max: " + Math.round(recipe.getMaxes().get(i) * 100) + "%"));
            }

        });

        for (int i = 0; i < ingredients.getOutputs(VanillaTypes.ITEM).size(); i++) {
            List<ItemStack> stacks = ingredients.getOutputs(VanillaTypes.ITEM).get(i);
            recipeLayout.getItemStacks().init(index + i + 1, false, 2 + 18 * i, 110);
            recipeLayout.getItemStacks().set(index + i + 1, stacks);
            index += 1;
        }

        recipeLayout.getFluidStacks().addTooltipCallback((i, b, stack, list) -> {
            list.add(new TextComponent(recipe.getFluid().getAmount() + "mb"));
        });
    }
}
