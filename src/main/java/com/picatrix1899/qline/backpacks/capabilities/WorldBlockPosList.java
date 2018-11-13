package com.picatrix1899.qline.backpacks.capabilities;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.picatrix1899.qline.backpacks.utils.WorldBlockPos;

public class WorldBlockPosList implements IWorldBlockPosList
{
	
	private ArrayList<WorldBlockPos> positions = Lists.newArrayList();
	
	@Override
	public List<WorldBlockPos> getPositions()
	{
		return positions;
	}

	@Override
	public void addPosition(WorldBlockPos pos)
	{
		if(this.positions.contains(pos)) return;
		
		this.positions.add(pos);
	}

	@Override
	public void removePosition(WorldBlockPos pos)
	{
		if(this.positions.contains(pos)) return;
		
		this.positions.remove(pos);
	}

}
