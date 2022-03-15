package com.cannolicatfish.rankine.entities;

import com.cannolicatfish.rankine.blocks.FumaroleBlock;
import com.cannolicatfish.rankine.blocks.GasBlock;
import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.init.RankineItems;
import com.cannolicatfish.rankine.init.RankineTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class ReactiveItemEntity extends ItemEntity {

    private float radius;
    private boolean canBreakBlocks;
    private Item newItem;
    private Block releasedGas;

    public ReactiveItemEntity(EntityType<? extends ItemEntity> p_i50217_1_, World p_i50217_2_) {
        super(p_i50217_1_, p_i50217_2_);
        this.radius = 1f;
        this.canBreakBlocks = false;
    }

    public ReactiveItemEntity(World worldIn, double x, double y, double z) {
        this(EntityType.ITEM, worldIn);
        this.setPos(x, y, z);
        this.yRot = this.random.nextFloat() * 360.0F;
        this.radius = 1f;
        this.canBreakBlocks = false;
        this.setDeltaMovement(this.random.nextDouble() * 0.2D - 0.1D, 0.2D, this.random.nextDouble() * 0.2D - 0.1D);
    }

    public ReactiveItemEntity(World worldIn, double x, double y, double z, ItemStack stack) {
        super(EntityType.ITEM,worldIn);
        this.setPos(x, y, z);
        this.setItem(stack);
        this.radius = 1f;
        this.canBreakBlocks = false;
        this.lifespan = (stack.getItem() == null ? 6000 : stack.getEntityLifespan(worldIn));
        this.newItem = Items.AIR;
        this.releasedGas = Blocks.AIR;
    }

    public ReactiveItemEntity(World worldIn, double x, double y, double z, float radius, boolean canBreakBlocks, ItemStack stack, Item newItem, Block releasedGas) {
        super(EntityType.ITEM,worldIn);
        this.setPos(x, y, z);
        this.setItem(stack);
        this.radius = radius;
        this.canBreakBlocks = canBreakBlocks;
        this.lifespan = (stack.getItem() == null ? 6000 : stack.getEntityLifespan(worldIn));
        this.newItem = newItem;
        this.releasedGas = releasedGas;
    }

    @Override
    public void tick() {

        if (this.wasTouchingWater)
        {
            BlockPos pos = this.blockPosition();
            if (canBreakBlocks)
            {
                this.getCommandSenderWorld().explode(null, pos.getX(), pos.getY() + 16 * .0625D, pos.getZ(), this.radius, Explosion.Mode.BREAK);
            } else {
                this.getCommandSenderWorld().explode(null, pos.getX(), pos.getY() + 16 * .0625D, pos.getZ(), this.radius, Explosion.Mode.NONE);
            }
            if (!level.isClientSide && !level.restoringBlockSnapshots && !newItem.equals(Items.AIR)) {
                double d0 = (double) (level.random.nextFloat() * 0.5F) + 0.25D;
                double d1 = (double) (level.random.nextFloat() * 0.5F) + 0.25D;
                double d2 = (double) (level.random.nextFloat() * 0.5F) + 0.25D;
                ItemEntity itementity = new ItemEntity(level, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, new ItemStack(newItem,this.getItem().getCount()));
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            }
            if (!level.isClientSide && !releasedGas.equals(Blocks.AIR)) {
                for (int i = 0; i < Math.max(1,Math.round(this.radius)); i++) {
                    BlockPos close = BlockPos.findClosestMatch(pos,3,3,B -> level.isEmptyBlock(B) && !(level.getBlockState(B).getBlock() instanceof GasBlock)).orElse(null);
                    if (close == null) {
                        break;
                    } else {
                        level.setBlock(close,this.releasedGas.defaultBlockState(),2);
                    }
                }
            }
            this.remove();
        }
        super.tick();
    }

    public @NotNull IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
