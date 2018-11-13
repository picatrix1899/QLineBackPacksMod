package com.picatrix1899.qline.backpacks.utils;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Inspired from TLHPoE's Question: http://www.minecraftforge.net/forum/topic/55443-1112-itemstack-capabilities/
 */
public class GenericCapabilityProvider<H> implements ICapabilitySerializable<NBTBase>
{

	private final Capability<H> capability;
	private final EnumFacing facing;
	private final H instance;
	
	public GenericCapabilityProvider(Capability<H> capability, EnumFacing facing, H instance)
	{
		this.capability = capability;
		this.facing = facing;
		this.instance = instance;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		return capability == this.capability;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == this.capability ? (T) this.instance : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return this.capability.writeNBT(this.instance, this.facing);
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		this.capability.readNBT(this.instance, this.facing, nbt);
	}



}
