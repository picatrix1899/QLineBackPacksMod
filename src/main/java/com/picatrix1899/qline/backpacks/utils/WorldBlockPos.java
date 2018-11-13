package com.picatrix1899.qline.backpacks.utils;

import net.minecraft.util.math.BlockPos;

public class WorldBlockPos
{
	int x, y, z;
	int world;
	
	public WorldBlockPos(BlockPos pos, int worldId)
	{
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		this.world = worldId;
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getZ() { return this.z; }
	public int getWorld() { return this.world; }
	public BlockPos getBlockPos() { return new BlockPos(this.x, this.y, this.z); }
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof WorldBlockPos)
		{
			WorldBlockPos p = (WorldBlockPos)obj;
			
			return this.x == p.x && this.y == p.y && this.z == p.z && this.world == p.world;
		}
		
		return false;
	}
	
}
