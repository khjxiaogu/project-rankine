package com.cannolicatfish.rankine.blocks.templatetable;

import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.init.RankineItems;
import com.cannolicatfish.rankine.init.RankineRecipeTypes;
import com.cannolicatfish.rankine.init.RankineRecipes;
import com.cannolicatfish.rankine.items.AlloyTemplateItem;
import com.cannolicatfish.rankine.items.alloys.AlloyItem;
import com.cannolicatfish.rankine.recipe.AlloyingRecipe;
import com.cannolicatfish.rankine.recipe.helper.AlloyCustomHelper;
import com.cannolicatfish.rankine.util.PeriodicTableUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffers;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.INBT;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.cannolicatfish.rankine.init.RankineBlocks.TEMPLATE_TABLE_CONTAINER;
public class TemplateTableContainer extends Container {

    private IItemHandler playerInventory;
    private World world;
    public final IInventory inputInventory = new Inventory(8) {
        public void setChanged() {
            super.setChanged();
            TemplateTableContainer.this.slotsChanged(this);
        }
    };
    public final IInventory outputInventory = new Inventory(1);
    private ItemStack recipeOutput;
    private PlayerEntity player;
    private final IWorldPosCallable worldPosCallable;

    public TemplateTableContainer(int id, PlayerInventory playerInventory, PlayerEntity player) {
        this(id,playerInventory,player, IWorldPosCallable.NULL);
    }

    public TemplateTableContainer(int windowId, PlayerInventory playerInventory, PlayerEntity player, IWorldPosCallable wpos) {
        super(TEMPLATE_TABLE_CONTAINER,windowId);
        this.worldPosCallable = wpos;
        this.player = player;
        this.world = player.level;

        this.addSlot(new Slot(inputInventory,0,108,18));
        this.addSlot(new Slot(inputInventory,1,134,18));
        this.addSlot(new Slot(inputInventory,2,160,18));
        this.addSlot(new Slot(inputInventory,3,186,18));
        this.addSlot(new Slot(inputInventory,4,212,18));
        this.addSlot(new Slot(inputInventory,5,238,18));
        this.addSlot(new Slot(inputInventory,6,180,62));
        this.addSlot(new Slot(inputInventory,7,198,62));
        this.addSlot(new Slot(outputInventory,0,248,58) {
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            public ItemStack onTake(PlayerEntity player, ItemStack stack) {
                TemplateTableContainer.this.inputInventory.removeItem(6,1);
                TemplateTableContainer.this.inputInventory.removeItem(7,1);
                TemplateTableContainer.this.updateRecipeResultSlot();

                stack.getItem().onCraftedBy(stack, player.level, player);
                worldPosCallable.execute((p_216954_1_, p_216954_2_) -> {

                });
                return super.onTake(player, stack);
            }
        });
        this.playerInventory = new InvWrapper(playerInventory);
        layoutPlayerInventorySlots(108, 84);
    }

    public World getWorld() {
        return world;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if(slot != null && slot.hasItem())
        {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 9) {
                if (!this.moveItemStackTo(stack, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else if (!(index < 8)) {
                if (AlloyCustomHelper.hasElement(itemstack.getItem())) {
                    if (!this.moveItemStackTo(stack, 0, 6, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack.getItem() == Items.PAPER) {
                    if (!this.moveItemStackTo(stack, 6, 7, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack.getItem() instanceof DyeItem) {
                    if (!this.moveItemStackTo(stack, 7, 8, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 36) {
                    if (!this.moveItemStackTo(stack, 36, 45, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 45 && !this.moveItemStackTo(stack, 10, 36, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 9, 45, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
/*
    @OnlyIn(Dist.CLIENT)
    public AbstractMap.SimpleEntry<String[],Integer> getOutputString() {
        AlloyingRecipe recipe = world.getRecipeManager().getRecipe(RankineRecipeTypes.ALLOYING, this.inputInventory, world).orElse(null);
        if (recipe != null) {
            ItemStack stack = recipe.generateResult(world,this.inputInventory,3);
            INBT nbt = AlloyItem.getComposition(stack).getCompound(0).get("comp");
            if (nbt != null){
                return new AbstractMap.SimpleEntry<>(new String[]{new TranslationTextComponent(stack.getItem().getTranslationKey()).getString(),nbt.getString()},0x55FF55);
            } else {
                return new AbstractMap.SimpleEntry<>(new String[]{},0xffffff);
            }
        } else {
            String ret = RankineRecipes.generateAlloyString(this.inputInventory);
            return new AbstractMap.SimpleEntry<>(new String[]{"None", ret},0xFF5555);
        }
    }*/

    public void slotsChanged(IInventory inventoryIn) {

        if (this.inputInventory.getItem(6).getItem() == Items.PAPER &&
                (this.inputInventory.getItem(7).getItem() instanceof DyeItem))
        {
            AlloyingRecipe recipeIn = this.world.getRecipeManager().getRecipeFor(RankineRecipeTypes.ALLOYING, this.inputInventory, this.world).orElse(null);
            //calcPercentages();
            if (recipeIn != null) {
                ItemStack recipeOutput = recipeIn.generateResult(this.world,this.inputInventory, 3);
                if (!recipeOutput.isEmpty()) {
                    ItemStack st = new ItemStack(RankineItems.ALLOY_TEMPLATE.get());
                    AlloyTemplateItem.addTemplate(world,st, recipeIn, this.inputInventory, (DyeItem) this.inputInventory.getItem(7).getItem());

                    this.outputInventory.setItem(0, st);
                    return;

                }
            }

        }
        if (!this.outputInventory.getItem(0).isEmpty()) {
            this.outputInventory.setItem(0,ItemStack.EMPTY);
        }
    }


    private void updateRecipeResultSlot() {
        this.slotsChanged(inputInventory);
        this.broadcastChanges();
    }


    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(this.worldPosCallable, playerIn, RankineBlocks.TEMPLATE_TABLE.get());
    }

    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.worldPosCallable.execute((p_217068_2_, p_217068_3_) -> {
            this.clearContainer(playerIn, p_217068_2_, this.inputInventory);
        });
    }

    public List<AlloyingRecipe> getAlloyRecipes() {
        //&& !alloyingRecipe.cannotMake(player.inventory, this.world)
        return world.getRecipeManager().getAllRecipesFor(RankineRecipeTypes.ALLOYING).stream().filter(alloyingRecipe -> !alloyingRecipe.getElementList(this.world).isEmpty()).collect(Collectors.toList());
    }

    public void tryMoveItems(int p_217046_1_) {
        if (this.getAlloyRecipes().size() > p_217046_1_) {
            ItemStack itemstack = this.inputInventory.getItem(0);
            if (!itemstack.isEmpty()) {
                if (!this.moveItemStackTo(itemstack, 3, 39, true)) {
                    return;
                }

                this.inputInventory.setItem(0, itemstack);
            }

            ItemStack itemstack1 = this.inputInventory.getItem(1);
            if (!itemstack1.isEmpty()) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return;
                }

                this.inputInventory.setItem(1, itemstack1);
            }

            if (this.inputInventory.getItem(0).isEmpty() && this.inputInventory.getItem(1).isEmpty()) {

            }

        }
    }

    public void setCurrentRecipeIndex(int currentRecipeIndex) {

    }
}
