package org.techern.infinitewater;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

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
    public static final String VERSION = "1.0.0-SNAPSHOT";

    /**
     * The {@link Logger} for {@link InfinitelySpreadingWaterMod}
     *
     * @since 1.0.0
     */
    public static Logger LOGGER = null;

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
        LOGGER.info("WE ARE A GO " + MOD_ID);
    }
}
