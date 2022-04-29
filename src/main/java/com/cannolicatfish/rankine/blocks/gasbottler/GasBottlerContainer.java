package com.cannolicatfish.rankine.blocks.gasbottler;

import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.init.RankineContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
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

public class GasBottlerContainer extends Container {
    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;
    private final IIntArray data;

    public GasBottlerContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        this(windowId,world,pos,playerInventory,player,new Inventory(2), new IntArray(2));

    }
    public GasBottlerContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IInventory furnaceInventoryIn, IIntArray towerData) {
        super(RankineContainers.GAS_CONDENSER_CONTAINER.get(), windowId);
        tileEntity = world.getTileEntity(pos);
        assertInventorySize(furnaceInventoryIn, 2);
        assertIntArraySize(towerData, 2);
        this.playerEntity = player;
        this.data = towerData;
        this.playerInventory = new InvWrapper(playerInventory);

        this.addSlot(new Slot(furnaceInventoryIn, 0, 41, 32));
        this.addSlot(new Slot(furnaceInventoryIn, 1, 120, 32));

        layoutPlayerInventorySlots(8, 70);
        this.trackIntArray(towerData);
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
    public int getCookProgressScaled(int pixels)
    {
        int i = getCookTime();
        int j = getTotalCookTime();
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, RankineBlocks.GAS_BOTTLER.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index <= 1) {
                if (!this.mergeItemStack(stack, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else {
                if (index < 29) {
                    if (!this.mergeItemStack(stack, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 38 && !this.mergeItemStack(stack, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
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
        return 0;
    }


}
