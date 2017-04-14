package org.techern.infinitewater.items;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.techern.infinitewater.InfinitelySpreadingWaterMod;

import javax.annotation.Nullable;

/**
 * An {@link Item} used to place a {@link org.techern.infinitewater.blocks.BlockInfinitelySpreadingWater}
 *
 * @since 1.1.0
 */
public class ItemInfinitelySpreadingWaterBucket extends Item {


    /**
     * Creates a new {@link ItemInfinitelySpreadingWaterBucket}
     *
     * @since 1.1.0
     */
    public ItemInfinitelySpreadingWaterBucket() {
        this.maxStackSize = 1;
        this.setCreativeTab(InfinitelySpreadingWaterMod.SPREADING_WATER_TAB);

        this.setUnlocalizedName("infinitely_spreading_water_bucket");
        this.setRegistryName("infinitely_spreading_water_bucket");
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, false);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemstack, raytraceresult);
        if (ret != null) return ret;

        if (raytraceresult == null || raytraceresult.typeOfHit == null) {
            return new ActionResult<>(EnumActionResult.PASS, itemstack);
        }
        if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(EnumActionResult.PASS, itemstack);
        } else {
            BlockPos blockpos = raytraceresult.getBlockPos();

            if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
                return new ActionResult<>(EnumActionResult.FAIL, itemstack);
            }

            boolean flag1 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
            BlockPos destination = flag1 && raytraceresult.sideHit == EnumFacing.UP ? blockpos : blockpos.offset(raytraceresult.sideHit);

            if (!playerIn.canPlayerEdit(destination, raytraceresult.sideHit, itemstack)) {
                return new ActionResult<>(EnumActionResult.FAIL, itemstack);
            } else if (this.placeBlock(playerIn, worldIn, destination)) {
                if (playerIn.isCreative()) {
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
                } else {
                    return new ActionResult<>(EnumActionResult.SUCCESS, new ItemStack(Items.BUCKET));
                }
            } else {
                return new ActionResult<>(EnumActionResult.FAIL, itemstack);
            }
        }
    }

    boolean placeBlock(@Nullable EntityPlayer player, World worldIn, BlockPos posIn) {

        IBlockState iblockstate = worldIn.getBlockState(posIn);
        Material material = iblockstate.getMaterial();
        boolean isMaterialLiquidOrGas = !material.isSolid();
        boolean isMaterialReplaceable = iblockstate.getBlock().isReplaceable(worldIn, posIn);

        if (!worldIn.isAirBlock(posIn) && !isMaterialLiquidOrGas && !isMaterialReplaceable) {
            return false;
        } else {
            if (worldIn.provider.doesWaterVaporize()) {
                int x = posIn.getX();
                int y = posIn.getY();
                int z = posIn.getZ();
                worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

                for (int k = 0; k < 8; ++k) {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) x + Math.random(), (double) y + Math.random(), (double) z + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
                }
            } else {
                if (!worldIn.isRemote && (isMaterialLiquidOrGas || isMaterialReplaceable) && !material.isLiquid()) {
                    worldIn.destroyBlock(posIn, true);
                }

                worldIn.playSound(player, posIn, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                worldIn.setBlockState(posIn, InfinitelySpreadingWaterMod.INFINITELY_SPREADING_WATER_BLOCK.getDefaultState(), 11);

                worldIn.scheduleBlockUpdate(posIn, InfinitelySpreadingWaterMod.INFINITELY_SPREADING_WATER_BLOCK, 100, 2);
            }

            return true;
        }
    }
}


