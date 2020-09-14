package com.wc.cybermen.capability;

import com.wc.cybermen.Cybermen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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

	public static void initCapability(){
		CapabilityManager.INSTANCE.register(ICyber.class, new Capability.IStorage<ICyber>()
		{
			@Nullable @Override public INBT writeNBT(Capability<ICyber> capability, ICyber instance, Direction side)
			{
				if(instance != null)
					return instance.serializeNBT();
				return null;
			}

			@Override public void readNBT(Capability<ICyber> capability, ICyber instance, Direction side, INBT nbt)
			{
				if (instance != null)
					instance.deserializeNBT((CompoundNBT) nbt);

			}
		}, CapabilityCyber::new);
	}

	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
		if(event.getObject() instanceof LivingEntity && !event.getObject().getCapability(CYBER_CAPABILITY).isPresent())
			event.addCapability(new ResourceLocation(Cybermen.MODID, "cyber"), new CyberProvider(new CapabilityCyber()));
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

	static class CyberProvider implements ICapabilitySerializable<CompoundNBT> {

		public final ICyber container;
		private LazyOptional<ICyber> optional;

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
