package com.wc.cybermen.init;

import com.wc.cybermen.Cybermen;
import com.wc.cybermen.block.ControllerBlock;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class CTiles {

    public static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Cybermen.MODID);

    public static RegistryObject<TileEntityType<?>> CONTROLLER_TILE = TILES.register("controller", () -> registerTiles(ControllerBlock.ControllerTileEntity::new, CBlocks.CONTROLLER_BLOCK.get()));

    // Tile Creation
    private static <T extends TileEntity> TileEntityType<T> registerTiles(Supplier<T> tile, Block... validBlock) {
        return TileEntityType.Builder.create(tile, validBlock).build(null);
    }

}
