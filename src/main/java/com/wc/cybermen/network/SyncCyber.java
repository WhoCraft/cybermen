package com.wc.cybermen.network;

import com.wc.cybermen.common.capability.CapabilityCyber;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Created by Nictogen on 9/14/20.
 */
public class SyncCyber {
    public int entityID;
    public CompoundNBT nbt;

    public SyncCyber(int entityID, CompoundNBT nbt) {
        this.entityID = entityID;
        this.nbt = nbt;
    }

    public SyncCyber(PacketBuffer buf) {
        this.entityID = buf.readInt();
        this.nbt = buf.readCompoundTag();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(this.entityID);
        buf.writeCompoundTag(this.nbt);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        Entity entity = Minecraft.getInstance().world.getEntityByID(this.entityID);

        ctx.get().enqueueWork(() -> {
            if (entity != null)
                entity.getCapability(CapabilityCyber.CYBER_CAPABILITY).ifPresent((c) -> c.deserializeNBT(this.nbt));
        });
        ctx.get().setPacketHandled(true);
    }
}
