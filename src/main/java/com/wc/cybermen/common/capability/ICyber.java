package com.wc.cybermen.common.capability;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by Nictogen on 9/14/20.
 */

public interface ICyber extends INBTSerializable<CompoundNBT> {
    boolean isCyber();

    void setCyber(boolean isCyber);

    void syncToClients(ServerPlayerEntity entity);
}
