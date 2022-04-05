package com.cannolicatfish.rankine.blocks.crucible;

import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.init.RankineContainers;
import com.cannolicatfish.rankine.init.RankineRecipeTypes;
import com.cannolicatfish.rankine.recipe.CrucibleRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CrucibleContainer extends Container {
    private final IInventory furnaceInventory;
    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;
    private World world;
    private final IIntArray data;

    public CrucibleContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        this(windowId,world,pos,playerInventory,player,new Inventory(6),new IntArray(3));



    }
    public CrucibleContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IInventory furnaceInventoryIn, IIntArray furnaceData) {
        super(RankineContainers.CRUCIBLE_CONTAINER.get(), windowId);
        tileEntity = world.getTileEntity(pos);
        assertInventorySize(furnaceInventoryIn, 6);
        assertIntArraySize(furnaceData, 3);
        this.playerEntity = player;
        this.data = furnaceData;
        this.furnaceInventory = furnaceInventoryIn;
        this.playerInventory = new InvWrapper(playerInventory);
        this.world = playerEntity.world;

        this.addSlot(new Slot(furnaceInventory, 0, 53, 23));
        this.addSlot(new Slot(furnaceInventory, 1, 71,16));
        this.addSlot(new Slot(furnaceInventory, 2, 88,16));
        this.addSlot(new Slot(furnaceInventory, 3, 106,23));
        this.addSlot(new Slot(furnaceInventory, 4, 79,53));
        this.addSlot(new Slot(furnaceInventory, 5, 49,53));

        layoutPlayerInventorySlots(8, 84);

        this.trackIntArray(furnaceData);
    }


    @OnlyIn(Dist.CLIENT)
    public int getCookTime(){
        return this.data.get(0);
    }

    @OnlyIn(Dist.CLIENT)
    public int getTotalCookTime(){
        return this.data.get(1);
    }

    @OnlyIn(Dist.CLIENT)
    public int getHeatStatus(){
        return this.data.get(2);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, RankineBlocks.CRUCIBLE_BLOCK.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 4 || index == 5) {
                if (!this.mergeItemStack(stack, 6, 42, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else if (index > 5) {
                if (hasRecipe(stack)) {
                    if (!this.mergeItemStack(stack, 0, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index < 33) {
                    if (!this.mergeItemStack(stack, 33, 42, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 42 && !this.mergeItemStack(stack, 6, 33, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(stack, 6, 42, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

    protected boolean hasRecipe(ItemStack stack) {
        for (CrucibleRecipe recipe : this.world.getRecipeManager().getRecipesForType(RankineRecipeTypes.CRUCIBLE)) {
            for (Ingredient i : recipe.getIngredients()) {
                if (i.test(stack)) {
                    return true;
                }
            }
        }
        return false;
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

    public int getOutputSlot() {
        return 4;
    }


    @OnlyIn(Dist.CLIENT)
    public int getCookProgressScaled(int pixels)
    {
        int i = getCookTime();
        int j = getTotalCookTime();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }



}

