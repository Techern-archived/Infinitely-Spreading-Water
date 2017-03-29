package org.techern.infinitewater.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import org.techern.infinitewater.InfinitelySpreadingWaterMod;

/**
 * An extension of {@link CommonProxy} for the client
 *
 * @since 1.0.0
 */
public class ClientProxy extends CommonProxy {

    /**
     * Registers an {@link Item} in the {@link ItemModelMesher}
     *
     * @param item     The {@link Item} being registered
     * @param metadata The metadata of said item
     * @param itemName The item's name
     *
     * @since 1.0.0
     */
    @Override
    public void registerItemModelMesher(Item item, int metadata, String itemName) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(InfinitelySpreadingWaterMod.MOD_ID + ":" + itemName));
    }

    /**
     * Registers an {@link Item} in the {@link ItemModelMesher}
     *
     * @param item     The {@link Item} being registered
     * @param metadata The metadata of said item
     * @param itemName The item's name
     * @param metadataNameAndValue The name and value of the metadata
     *
     * @since 1.0.0
     */
    @Override
    public void registerItemModelMesher(Item item, int metadata, String itemName, String metadataNameAndValue) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(InfinitelySpreadingWaterMod.MOD_ID + ":" + itemName, metadataNameAndValue));
    }
}