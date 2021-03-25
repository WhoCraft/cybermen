package com.wc.cybermen.common.tiles;

import com.wc.cybermen.common.init.CTiles;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ControllerTileEntity extends TileEntity {
    public ControllerTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ControllerTileEntity() {
        super(CTiles.CONTROLLER_TILE.get());
    }
}
