package com.picatrix1899.qline.backpacks.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public class NBTUtils
{
	public static NBTTagCompound setWorldBlockPos(WorldBlockPos pos)
	{
		NBTTagCompound c = new NBTTagCompound();
		c.setInteger("x", pos.getX());
		c.setInteger("y", pos.getY());
		c.setInteger("z", pos.getZ());
		c.setInteger("world", pos.getWorld());
		
		return c;
	}
	
	public static WorldBlockPos getWorldBlockPos(NBTTagCompound nbt)
	{
		WorldBlockPos pos = null;
		
		if(nbt.hasKey("x") && nbt.hasKey("y") && nbt.hasKey("z") && nbt.hasKey("world"))
		{
			int x = nbt.getInteger("x");
			int y = nbt.getInteger("y");
			int z = nbt.getInteger("z");
			int world = nbt.getInteger("world");
			
			pos = new WorldBlockPos(new BlockPos(x, y, z), world);
		}
			
		return pos;
	}
}
