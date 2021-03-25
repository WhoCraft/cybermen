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
        super(Block.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL));
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
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.level.isClientSide) {
            Minecraft.getInstance().setScreen(new ControllerTaskGui());
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}
