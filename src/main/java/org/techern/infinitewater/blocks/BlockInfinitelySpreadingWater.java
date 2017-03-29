package org.techern.infinitewater.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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
        super(Material.WATER);

        this.setUnlocalizedName("infinitely_spreading_water");
        this.setRegistryName("infinitely_spreading_water");
        this.setBlockUnbreakable();
    }
}