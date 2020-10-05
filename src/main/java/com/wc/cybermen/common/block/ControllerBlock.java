package com.wc.cybermen.common.block;

import com.wc.cybermen.client.gui.ControllerTaskGui;
import com.wc.cybermen.common.tiles.ControllerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Nictogen on 9/14/20.
 */
public class ControllerBlock extends Block {
    public ControllerBlock() {
        super(Block.Properties.create(Material.IRON, MaterialColor.IRON));
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ControllerTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.world.isRemote) {
            Minecraft.getInstance().displayGuiScreen(new ControllerTaskGui());
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
