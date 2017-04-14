package org.techern.infinitewater;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;
import org.techern.infinitewater.blocks.BlockInfinitelySpreadingWater;
import org.techern.infinitewater.items.ItemInfinitelySpreadingWaterBucket;
import org.techern.infinitewater.proxy.CommonProxy;

/**
 * A {@link Mod} for infinitely spreading water, similar to Minecraft Alpha builds
 *
 * @since 1.0.0
 */
@Mod(modid = InfinitelySpreadingWaterMod.MOD_ID, version = InfinitelySpreadingWaterMod.VERSION)
public class InfinitelySpreadingWaterMod
{
    /**
     * The mod ID
     *
     * @since 1.0.0
     */
    public static final String MOD_ID = "infinitely_spreading_water";

    /**
     * The version of the {@link InfinitelySpreadingWaterMod}
     *
     * @since 1.0.0
     */
    public static final String VERSION = "1.1.0-SNAPSHOT";

    /**
     * The {@link Logger} for {@link InfinitelySpreadingWaterMod}
     *
     * @since 1.0.0
     */
    public static Logger LOGGER = null;

    /**
     * A {@link SidedProxy} of either {@link CommonProxy} or {@link org.techern.infinitewater.proxy.ClientProxy}
     *
     * @since 0.0.1
     */
    @SidedProxy(clientSide = "org.techern.infinitewater.proxy.ClientProxy",
            serverSide = "org.techern.infinitewater.proxy.CommonProxy")
    public static CommonProxy PROXY;

    /**
     * The {@link CreativeTabs} for {@link InfinitelySpreadingWaterMod}
     *
     * @since 1.1.0
     */
    public static CreativeTabs SPREADING_WATER_TAB = new CreativeTabs("spreading_water") {

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(INFINITELY_SPREADING_WATER_BUCKET);
        }
    };

    /**
     * The instance of {@link BlockInfinitelySpreadingWater}
     *
     * @since 1.0.0
     */
    public static Block INFINITELY_SPREADING_WATER_BLOCK = new BlockInfinitelySpreadingWater();

    /**
     * The instance of {@link ItemInfinitelySpreadingWaterBucket}
     *
     * @since 1.1.0
     */
    public static Item INFINITELY_SPREADING_WATER_BUCKET = new ItemInfinitelySpreadingWaterBucket();

    /**
     * Called when the {@link FMLPreInitializationEvent} fires
     *
     * @param event The {@link FMLPreInitializationEvent}
     *
     * @since 1.0.0
     */
    @EventHandler
    public void preInitialize(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        LOGGER.debug("Logger set up for " + MOD_ID);
    }

    /**
     * Called when the {@link FMLInitializationEvent} fires
     *
     * @param event The {@link FMLInitializationEvent}
     *
     * @since 1.0.0
     */
    @EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        GameRegistry.register(INFINITELY_SPREADING_WATER_BLOCK);
        //GameRegistry.register(new ItemBlock(INFINITELY_SPREADING_WATER_BLOCK).setRegistryName(INFINITELY_SPREADING_WATER_BLOCK.getRegistryName()));


        GameRegistry.register(INFINITELY_SPREADING_WATER_BUCKET);

        PROXY.registerItemModelMesher(INFINITELY_SPREADING_WATER_BUCKET, 0, "infinitely_spreading_water_bucket", "inventory");
    }
}
