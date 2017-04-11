package org.techern.infinitewater.proxy;

import net.minecraft.item.Item;

/**
 * A {@link net.minecraftforge.fml.common.SidedProxy} for the {@link org.techern.infinitewater.InfinitelySpreadingWaterMod}
 *
 * @since 1.0.0
 */
public class CommonProxy {

    /**
     * Registers an {@link Item} in the {@link net.minecraft.client.renderer.ItemModelMesher}
     *
     * @param item The {@link Item} being registered
     * @param metadata The metadata of said item
     * @param itemName The item's name
     *
     * @since 1.0.0
     */
    public void registerItemModelMesher(Item item, int metadata, String itemName) {

    }

    /**
     * Registers an {@link Item} in the {@link net.minecraft.client.renderer.ItemModelMesher}
     *
     * @param item     The {@link Item} being registered
     * @param metadata The metadata of said item
     * @param itemName The item's name
     * @param metadataNameAndValue The name and value of the metadata
     *
     * @since 1.0.0
     */
    public void registerItemModelMesher(Item item, int metadata, String itemName, String metadataNameAndValue) {

    }


}

