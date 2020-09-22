package com.wc.cybermen;

import com.wc.cybermen.block.ControllerBlock;
import com.wc.cybermen.capability.CapabilityCyber;
import com.wc.cybermen.network.SyncCyber;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Cybermen.MODID)
public class Cybermen
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "wccybermen";

    public static SimpleChannel NETWORK_CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> "1.0", "1.0"::equals, "1.0"::equals);
    public static Block controllerBlock = new ControllerBlock();
    public static TileEntityType<ControllerBlock.ControllerTileEntity> controllerTEType = null;

    public Cybermen() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);


        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        MinecraftForge.EVENT_BUS.register(this);

        NETWORK_CHANNEL.registerMessage(0, SyncCyber.class, SyncCyber::toBytes, SyncCyber::new, SyncCyber::handle);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityCyber.initCapability();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> evt) {
        evt.getRegistry().register(new BlockItem(controllerBlock, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName(MODID, "controller_block"));
    }

    @SubscribeEvent
    public void registerBlock(RegistryEvent.Register<Block> evt) {
        controllerBlock.setRegistryName(MODID, "controller_block");
        evt.getRegistry().register(controllerBlock);
    }

    @SubscribeEvent
    public void registerTE(RegistryEvent.Register<TileEntityType<?>> evt) {
        controllerTEType = TileEntityType.Builder.create(ControllerBlock.ControllerTileEntity::new, controllerBlock).build(null);
        controllerTEType.setRegistryName(MODID, "controller_block_te");
        evt.getRegistry().register(controllerTEType);
    }
}
