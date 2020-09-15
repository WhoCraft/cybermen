package com.wc.cybermen.block;

import com.wc.cybermen.Cybermen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

/**
 * Created by Nictogen on 9/14/20.
 */
public class ControllerBlock extends Block
{
	public ControllerBlock()
	{
		super(Block.Properties.create(Material.IRON, MaterialColor.IRON));
	}

	public static class ControllerTileEntity extends TileEntity {

		public ControllerTileEntity()
		{
			super(Cybermen.controllerTEType);
		}

		@Override public void read(CompoundNBT compound)
		{
			super.read(compound);
		}

		@Override public CompoundNBT write(CompoundNBT compound)
		{
			return super.write(compound);
		}
	}

	@Override public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Nullable @Override public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new ControllerTileEntity();
	}
}
