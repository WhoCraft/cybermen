package com.wc.cybermen;

import com.wc.cybermen.common.capability.CapabilityCyber;
import com.wc.cybermen.common.init.CBlocks;
import com.wc.cybermen.common.init.CEntities;
import com.wc.cybermen.common.init.CItems;
import com.wc.cybermen.common.init.CTiles;
import com.wc.cybermen.data.EnglishLang;
import com.wc.cybermen.data.ItemModelCreation;
import com.wc.cybermen.network.SyncCyber;
import com.wc.cybermen.util.ClientUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Cybermen.MODID)
public class Cybermen {
    public static final String MODID = "wccybermen";
    private static final Logger LOGGER = LogManager.getLogger();
    public static SimpleChannel NETWORK_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> "1.0", "1.0"::equals, "1.0"::equals);

    public Cybermen() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);
        modEventBus.addListener(this::doClientStuff);


        modEventBus.register(this);
        MinecraftForge.EVENT_BUS.register(this);

        NETWORK_CHANNEL.registerMessage(0, SyncCyber.class, SyncCyber::toBytes, SyncCyber::new, SyncCyber::handle);
    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityCyber.initCapability();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientUtil.init();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onNewRegistries(RegistryEvent.NewRegistry e) {
        CTiles.TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CBlocks.BLOCK_ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent e) {
        e.getGenerator().addProvider(new EnglishLang(e.getGenerator()));
        e.getGenerator().addProvider(new ItemModelCreation(e.getGenerator()));
    }
}
