package com.cannolicatfish.rankine.items.alloys;

import com.cannolicatfish.rankine.init.Config;
import com.cannolicatfish.rankine.init.RankineEnchantments;
import com.cannolicatfish.rankine.items.tools.CrowbarItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class AlloyCrowbarItem extends CrowbarItem implements IAlloyTool{
    private final String defaultComposition;
    private final ResourceLocation defaultAlloyRecipe;
    public AlloyCrowbarItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, String defaultCompositionIn, @Nullable ResourceLocation defaultAlloyRecipeIn, Item.Properties builderIn) {
        super(tier, (int) attackDamageIn, attackSpeedIn, builderIn);
        this.defaultComposition = defaultCompositionIn;
        this.defaultAlloyRecipe = defaultAlloyRecipeIn;
    }

    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem(calcDurabilityLoss(stack,attacker.getEntityWorld(),attacker,true), attacker, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F) {
            stack.damageItem(calcDurabilityLoss(stack,worldIn,entityLiving,true), entityLiving, (entity) -> {
                entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }

        if ((canHarvestBlock(state) || (canHarvestWithRetrieval(stack,state) && worldIn.getTileEntity(pos) == null && worldIn.getFluidState(pos).isEmpty())) && !worldIn.isRemote && !stack.isEmpty() && worldIn.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && !worldIn.restoringBlockSnapshots)
        {
            double d0 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25D;
            double d1 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25D;
            double d2 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25D;
            ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, new ItemStack(state.getBlock().asItem(), 1));
            itementity.setDefaultPickupDelay();
            worldIn.addEntity(itementity);
            worldIn.removeBlock(pos, false);
            SoundType soundtype = worldIn.getBlockState(pos).getSoundType(worldIn, pos, null);
            worldIn.playSound(pos.getX(),pos.getY(),pos.getZ(), soundtype.getBreakSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F, false);
        }

        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return getDamage(stack) * 1f / this.getAlloyDurability(stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return this.getAlloyDurability(stack);
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return this.getAlloyEnchantability(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        DecimalFormat df = Util.make(new DecimalFormat("##.#"), (p_234699_0_) -> {
            p_234699_0_.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
        });
        if (this.isAlloyInit(stack))
        {
            if (!Screen.hasShiftDown())
            {
                tooltip.add((new StringTextComponent("Hold shift for details...")).mergeStyle(TextFormatting.GRAY));
            }
            if (Screen.hasShiftDown())
            {
                if (IAlloyItem.getAlloyComposition(stack).isEmpty()) {
                    tooltip.add((new StringTextComponent("Any Composition").mergeStyle(TextFormatting.GOLD)));
                } else {
                    tooltip.add((new StringTextComponent("Composition: " + IAlloyItem.getAlloyComposition(stack)).mergeStyle(TextFormatting.GOLD)));
                }

                if (!IAlloyItem.needsRefresh(stack)) {
                    float eff = getAlloyEfficiency(stack);

                    tooltip.add((new StringTextComponent("Durability: " + (getAlloyDurability(stack) - getDamage(stack)) + "/" + getAlloyDurability(stack))).mergeStyle(TextFormatting.DARK_GREEN));
                    tooltip.add((new StringTextComponent("Harvest Level: " + (getAlloyHarvestLevel(stack)))).mergeStyle(TextFormatting.GRAY));
                    tooltip.add((new StringTextComponent("Mining Speed: " + df.format(eff))).mergeStyle(TextFormatting.GRAY));
                    tooltip.add((new StringTextComponent("Enchantability: " + getAlloyEnchantability(stack))).mergeStyle(TextFormatting.GRAY));
                    if (Config.ALLOYS.ALLOY_CORROSION.get())
                    {
                        tooltip.add((new StringTextComponent("Corrosion Resistance: " + (df.format(getCorrResist(stack) * 100)) + "%")).mergeStyle(TextFormatting.GRAY));
                    }
                    if (Config.ALLOYS.ALLOY_HEAT.get())
                    {
                        tooltip.add((new StringTextComponent("Heat Resistance: " + (df.format(getHeatResist(stack) * 100)) + "%")).mergeStyle(TextFormatting.GRAY));
                    }
                    if (Config.ALLOYS.ALLOY_TOUGHNESS.get())
                    {
                        tooltip.add((new StringTextComponent("Toughness: " + (df.format(getToughness(stack) * 100)) + "%")).mergeStyle(TextFormatting.GRAY));
                    }
                }

                if (flagIn.isAdvanced()) {
                    if (IAlloyItem.getAlloyRecipe(stack) != null) {
                        tooltip.add((new StringTextComponent("Recipe: " + (IAlloyItem.getAlloyRecipe(stack))).mergeStyle(TextFormatting.LIGHT_PURPLE)));
                    } else {
                        tooltip.add((new StringTextComponent("No Recipe Defined").mergeStyle(TextFormatting.LIGHT_PURPLE)));
                    }

                }
            }
        }
    }

    /*
        @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        CompoundNBT nbt = stack.getTag();
        if (getComposition(stack).size() > 0 && alloy.getDefComposition().equals("80Hg-20Au") && nbt != null && !nbt.getString("nameAdd").isEmpty() && !nbt.getString("nameAdd").equals("false")) {
            String name = new TranslationTextComponent(this.getTranslationKey(stack)).getString();
            String[] sp = name.split(" ");
            if (sp.length > 0) {
                name = sp[sp.length - 1];
            }
            return new StringTextComponent(stack.getTag().getString("nameAdd") + " " + name);
        }
        return super.getDisplayName(stack);
    }

*/
    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        this.applyAlloyEnchantments(stack,worldIn);
        super.onCreated(stack, worldIn, playerIn);
    }


    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!this.isAlloyInit(stack)) {
            this.createAlloyNBT(stack,worldIn,this.defaultComposition,this.defaultAlloyRecipe,null);
            this.applyAlloyEnchantments(stack,worldIn);
        } else if (IAlloyItem.needsRefresh(stack)) {
            this.createAlloyNBT(stack,worldIn,IAlloyItem.getAlloyComposition(stack),IAlloyItem.getAlloyRecipe(stack),null);
        }
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
/*
    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || group == ProjectRankine.setup.rankineTools) {
            ItemStack stack = getAlloyItemStack(new AlloyData(alloy.getDefComposition()),this.getItem());
            for (Enchantment e: getEnchantments(returnCompositionString(stack,this.alloy),stack.getItem(),this.alloy))
            {
                int enchLvl = alloy.getEnchantmentLevel(e,getAlloyEnchantability(stack));
                if (enchLvl > 0) {
                    stack.addEnchantment(e,enchLvl);
                }
            }
            items.add(stack);
        }
    }
*/

    @Override
    public String getDefaultComposition() {
        return this.defaultComposition;
    }

    @Override
    public ResourceLocation getDefaultRecipe() {
        return this.defaultAlloyRecipe;
    }
}