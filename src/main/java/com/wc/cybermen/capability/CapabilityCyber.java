package com.wc.cybermen.capability;

import com.wc.cybermen.Cybermen;
import com.wc.cybermen.network.SyncCyber;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Nictogen on 9/14/20.
 */
@Mod.EventBusSubscriber
public class CapabilityCyber implements ICyber
{
	@CapabilityInject(ICyber.class)
	public static Capability<ICyber> CYBER_CAPABILITY = null;

	public boolean isCyber;

	public Entity entity;

	public CapabilityCyber(Entity entity){
		this.entity = entity;
	}

	public static void initCapability(){
		CapabilityManager.INSTANCE.register(ICyber.class, new Capability.IStorage<ICyber>()
		{
			@Nullable @Override public INBT writeNBT(Capability<ICyber> capability, ICyber instance, Direction side)
			{
				if (instance != null)
					return instance.serializeNBT();
				return null;
			}

			@Override public void readNBT(Capability<ICyber> capability, ICyber instance, Direction side, INBT nbt)
			{
				if (instance != null)
					instance.deserializeNBT((CompoundNBT) nbt);

			}
		}, () -> new CapabilityCyber(null));
	}

	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
		if(event.getObject() instanceof LivingEntity && !event.getObject().getCapability(CYBER_CAPABILITY).isPresent())
			event.addCapability(new ResourceLocation(Cybermen.MODID, "cyber"), new CyberProvider(new CapabilityCyber(event.getObject())));
	}

	@Override public CompoundNBT serializeNBT()
	{
		CompoundNBT nbt = new CompoundNBT();
		nbt.putBoolean("cyber", this.isCyber);
		return nbt;
	}

	@Override public void deserializeNBT(CompoundNBT nbt)
	{
		this.isCyber = nbt.getBoolean("cyber");
	}

	@Override public boolean isCyber()
	{
		return this.isCyber;
	}

	@Override public void setCyber(boolean isCyber)
	{
		this.isCyber = isCyber;
	}

	@Override public void syncToClients(ServerPlayerEntity entity)
	{
		if(entity == null){
			Cybermen.NETWORK_CHANNEL.send(PacketDistributor.ALL.noArg(), new SyncCyber(this.entity.getEntityId(), this.serializeNBT()));
		} else {
			Cybermen.NETWORK_CHANNEL.send(PacketDistributor.PLAYER.with(() -> entity), new SyncCyber(this.entity.getEntityId(), this.serializeNBT()));
		}
	}

	static class CyberProvider implements ICapabilitySerializable<CompoundNBT> {

		public final ICyber container;
		private final LazyOptional<ICyber> optional;

		public CyberProvider(ICyber container){
			this.container = container;
			this.optional = LazyOptional.of(() -> container);
		}

		@Nonnull @Override public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
		{
			if(cap == CYBER_CAPABILITY){
				return (LazyOptional<T>) optional;
			}
			return LazyOptional.empty();
		}

		@Override public CompoundNBT serializeNBT()
		{
			if(this.container != null)
				return this.container.serializeNBT();
			return new CompoundNBT();
		}

		@Override public void deserializeNBT(CompoundNBT nbt)
		{
			if(this.container != null)
				this.container.deserializeNBT(nbt);
		}
	}
}
