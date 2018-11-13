package com.picatrix1899.qline.backpacks.capabilities;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.picatrix1899.qline.backpacks.utils.GenericCapabilityProvider;
import com.picatrix1899.qline.backpacks.utils.NBTUtils;
import com.picatrix1899.qline.backpacks.utils.WorldBlockPos;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityWorldBlockPosList
{
	@CapabilityInject(IWorldBlockPosList.class)
	public static final Capability<IWorldBlockPosList> WORLD_BLOCK_POS_LIST_CAPABILITY = null;
	
	public static void register()
	{
		CapabilityManager.INSTANCE.register(IWorldBlockPosList.class, new Capability.IStorage<IWorldBlockPosList>() {
			
			@Override
			public NBTBase writeNBT(Capability<IWorldBlockPosList> capability, IWorldBlockPosList instance, EnumFacing side)
			{
				NBTTagCompound c = new NBTTagCompound();
				
				NBTTagList list = new NBTTagList();
				
				for(WorldBlockPos pos : instance.getPositions())
					list.appendTag(NBTUtils.setWorldBlockPos(pos));
				
				c.setTag("positions", list);
				
				return c;
			}
	
			@Override
			public void readNBT(Capability<IWorldBlockPosList> capability, IWorldBlockPosList instance, EnumFacing side, NBTBase nbt)
			{
				NBTTagCompound c = (NBTTagCompound)nbt;
				
				if(c.hasKey("positions"))
				{
					NBTTagList list = (NBTTagList)c.getTag("positions");
					
					for(int i = 0; i < list.tagCount(); i++)
					{
						instance.addPosition(NBTUtils.getWorldBlockPos(list.getCompoundTagAt(i)));
					}
				}
			}
		}, WorldBlockPosList::new);
		
	}
	
	public static ICapabilitySerializable<NBTBase> createProvider(IWorldBlockPosList instance)
	{
		return new GenericCapabilityProvider(WORLD_BLOCK_POS_LIST_CAPABILITY, null, instance);
	}
	
	public static ICapabilitySerializable<NBTBase> createProvider(IWorldBlockPosList instance, EnumFacing facing)
	{
		return new GenericCapabilityProvider(WORLD_BLOCK_POS_LIST_CAPABILITY, facing, instance);
	}
}
