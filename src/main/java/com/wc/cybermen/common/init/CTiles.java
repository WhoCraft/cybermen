package com.wc.cybermen.common.init;

import com.wc.cybermen.Cybermen;
import com.wc.cybermen.common.tiles.ControllerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class CTiles {

    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Cybermen.MODID);

    public static RegistryObject<TileEntityType<?>> CONTROLLER_TILE = TILES.register("controller", () -> registerTiles(ControllerTileEntity::new, CBlocks.CONTROLLER_BLOCK.get()));

    // Tile Creation
    private static <T extends TileEntity> TileEntityType<T> registerTiles(Supplier<T> tile, Block... validBlock) {
        return TileEntityType.Builder.of(tile, validBlock).build(null);
    }

}
