package com.cannolicatfish.rankine.blocks.crucible;


import com.cannolicatfish.rankine.blocks.charcoalpit.CharcoalPitTile;
import com.cannolicatfish.rankine.init.RankineRecipeTypes;
import com.cannolicatfish.rankine.init.RankineTags;
import com.cannolicatfish.rankine.recipe.CrucibleRecipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static com.cannolicatfish.rankine.init.RankineBlocks.CRUCIBLE_TILE;

public class CrucibleTile extends BlockEntity implements WorldlyContainer, MenuProvider {

    private static final int[] SLOTS_UP = new int[]{0,1};
    private static final int[] SLOTS_DOWN = new int[]{4, 5};
    private static final int[] SLOTS_HORIZONTAL = new int[]{2,3};
    public CrucibleTile(BlockPos posIn, BlockState stateIn) {
        super(CRUCIBLE_TILE, posIn, stateIn);
    }
    protected NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);
    private int cookTime;
    private int cookTimeTotal;
    private int color = 16777215;
    private int heatPower = 0;
    private final ContainerData furnaceData = new ContainerData(){
        public int get(int index)
        {
            switch(index)
            {
                case 0:
                    return CrucibleTile.this.cookTime;
                case 1:
                    return CrucibleTile.this.cookTimeTotal;
                case 2:
                    return CrucibleTile.this.heatPower;
                default:
                    return 0;
            }
        }

        public void set(int index, int value)
        {
            switch(index)
            {
                case 0:
                    CrucibleTile.this.cookTime = value;
                    break;
                case 1:
                    CrucibleTile.this.cookTimeTotal = value;
                    break;
                case 2:
                    CrucibleTile.this.heatPower = value;
            }
        }

        public int getCount() {
            return 3;
        }
    };

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt,this.items);
        this.cookTime = nbt.getInt("CookTime");
        this.cookTimeTotal = nbt.getInt("CookTimeTotal");
        this.color = nbt.getInt("color");
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.cookTimeTotal);
        compound.putInt("color", this.color);
        ContainerHelper.saveAllItems(compound, this.items);
    }

    public static void tick(Level level, BlockPos pos, BlockState bs, CrucibleTile tile) {
        boolean flag = tile.isHeated(tile.worldPosition,level);
        boolean flag1 = tile.isCooking();
        boolean flag2 = false;

        if (!level.isClientSide) {
            if (!flag1) {
                level.setBlock(tile.worldPosition, bs.setValue(CrucibleBlock.FLUID, false), 3);
            } else if (tile.getTileData().getInt("color") != tile.color) {
                tile.getTileData().putInt("color",tile.color);
                level.setBlock(tile.worldPosition, bs.setValue(CrucibleBlock.FLUID, tile.isCooking()), 3);
            }
            ItemStack[] inputs = new ItemStack[]{tile.items.get(0), tile.items.get(1), tile.items.get(2), tile.items.get(3)};
            if ((tile.isCooking() && flag) || !tile.items.get(0).isEmpty() && !tile.items.get(1).isEmpty() && !tile.items.get(2).isEmpty() && !tile.items.get(3).isEmpty()) {
                CrucibleRecipe irecipe = level.getRecipeManager().getRecipeFor(RankineRecipeTypes.CRUCIBLE, tile, level).orElse(null);
                if (tile.canSmelt(irecipe, tile)) {
                    if (tile.cookTime == 0) {
                        tile.cookTimeTotal = tile.getCookTime();
                        tile.color = irecipe.getColor();

                    }
                    ++tile.cookTime;
                    if (tile.cookTime >= tile.cookTimeTotal) {
                        ItemStack smelting = irecipe.generateResult(tile);
                        if (tile.items.get(4).getCount() > 0) {
                            tile.items.get(4).grow(smelting.getCount());
                        } else {
                            tile.items.set(4, smelting);
                        }

                        ItemStack extra = irecipe.getSecondaryOutput();
                        if (tile.items.get(5).getCount() > 0) {
                            tile.items.get(5).grow(extra.getCount());
                        } else {
                            tile.items.set(5, extra);
                        }

                        tile.cookTime = 0;
                        inputs[0].shrink(1);
                        inputs[1].shrink(1);
                        inputs[2].shrink(1);
                        inputs[3].shrink(1);
                        return;
                    }
                } else {
                    tile.cookTime = 0;
                }
            } else if ((flag) && tile.cookTime > 0) {
                tile.cookTime = Mth.clamp(tile.cookTime - 2, 0, tile.cookTimeTotal);
            }

            if (flag1 != tile.isCooking()) {
                flag2 = true;
                level.setBlock(tile.worldPosition, bs.setValue(CrucibleBlock.FLUID, tile.isCooking()), 3);
            }
        }

        if (flag2) {
            tile.setChanged();
        }

    }

    public int getCookTime() {
        CrucibleRecipe irecipe = this.level.getRecipeManager().getRecipeFor(RankineRecipeTypes.CRUCIBLE, this, this.level).orElse(null);
        if (irecipe != null) {
            return irecipe.getRecipeCookTime(this);
        } else {
            return 3200;
        }
    }

    private boolean isHeated(BlockPos pos, Level worldIn) {
        List<BlockPos> positions = Arrays.asList(pos.below(),pos.east(),pos.north(),pos.west(),pos.south());
        for (BlockPos p : positions) {
            if (worldIn.getBlockState(p).is(RankineTags.Blocks.HEAT_SOURCES)) {
                heatPower = 1;
                return true;
            }
        }
        heatPower = 0;
        return false;
    }

    public boolean isCooking()
    {
        return this.cookTime > 0;
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isCooking(CrucibleTile te)
    {
        return te.furnaceData.get(0) > 0;
    }

    private boolean canSmelt(@Nullable CrucibleRecipe recipeIn, Container inv)
    {
        if (recipeIn == null)
        {
            return false;
        }
        else
        {
            ItemStack result = recipeIn.generateResult(inv);
            ItemStack sec = recipeIn.getSecondaryOutput();
            if(result.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack output = this.items.get(4);
                ItemStack secondary = this.items.get(5);
                if(output.isEmpty() && (secondary.isEmpty() || sec.isEmpty())) {
                    this.cookTimeTotal = recipeIn.getRecipeCookTime(inv);
                    return true;
                }

                if(!output.sameItem(result) || !secondary.sameItem(sec))
                {
                    return false;
                }
                int res = output.getCount() + result.getCount();
                int res2 = secondary.getCount() + sec.getCount();
                if (ItemStack.tagMatches(output, result) && ItemStack.isSame(output, result) && ItemStack.tagMatches(secondary, sec) && ItemStack.isSame(secondary, sec)) {
                    this.cookTimeTotal = recipeIn.getRecipeCookTime(inv);
                    return res <= 64 && res2 <= 64 && res <= output.getMaxStackSize() && res2 <= secondary.getMaxStackSize();
                } else {
                    return false;
                }
            }
        }
    }

    net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        for (int x = 0; x < handlers.length; x++)
            handlers[x].invalidate();
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent(getType().getRegistryName().getPath());
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new CrucibleContainer(i, level, worldPosition, playerInventory, playerEntity, this, this.furnaceData);
    }


    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        } else {
            return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStackIn);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        ItemStack itemstack = this.items.get(index);
        boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (index == 0 && !flag) {
            this.cookTimeTotal = this.getCookTime();
            this.cookTime = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        switch (index)
        {
            case 0:
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }
}
