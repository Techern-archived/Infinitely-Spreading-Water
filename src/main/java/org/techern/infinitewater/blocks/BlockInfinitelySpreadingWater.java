package org.techern.infinitewater.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.techern.infinitewater.InfinitelySpreadingWaterMod;

import java.util.Random;

/**
 * A {@link Block} for infinitely spreading water
 *
 * @since 1.0.0
 */
public class BlockInfinitelySpreadingWater extends Block {

    /**
     * Creates a new {@link BlockInfinitelySpreadingWater}
     *
     * @since 1.0.0
     */
    public BlockInfinitelySpreadingWater() {
        super(Material.BARRIER);

        this.setCreativeTab(CreativeTabs.MISC);
        this.setUnlocalizedName("infinitely_spreading_water");
        this.setRegistryName("infinitely_spreading_water");
        this.setBlockUnbreakable();
    }

    /**
     * Called on a tick
     *
     * @param worldIn The {@link World} we're in
     * @param pos The {@link BlockPos} of this {@link BlockInfinitelySpreadingWater}
     * @param state The current {@link IBlockState}
     * @param rand The {@link Random} attach to the tick
     *
     * @since 1.0.0
     */
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);

        //Let's expand
        expand(worldIn, pos.north());
        expand(worldIn, pos.south());
        expand(worldIn, pos.east());
        expand(worldIn, pos.west());

        worldIn.setBlockState(pos, Blocks.WATER.getStateFromMeta(0));
    }

    /**
     * Attempt to expand by just replacing air
     *
     * @param world The {@link World}
     * @param position The {@link BlockPos} to replace
     *
     * @since 1.0.0
     */
    private void expand(World world, BlockPos position) {

        IBlockState state = world.getBlockState(position);


        if (state.equals(Blocks.AIR.getDefaultState())) {
            world.setBlockState(position, getDefaultState());
            world.scheduleBlockUpdate(position, this, 20, 1);
        }
    }

    /**
     * Called when this {@link BlockInfinitelySpreadingWater} is placed
     *
     * @param worldIn The {@link World}
     * @param pos The {@link BlockPos}
     * @param state The {@link IBlockState}
     * @param placer The {@link EntityLivingBase} that placed this {@link BlockInfinitelySpreadingWater}
     * @param stack The {@link ItemStack} used to place
     */
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        worldIn.scheduleBlockUpdate(pos, this, 100, 2);
    }
}
