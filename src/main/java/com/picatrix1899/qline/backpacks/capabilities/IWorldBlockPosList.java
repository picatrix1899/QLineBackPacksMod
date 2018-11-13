package com.picatrix1899.qline.backpacks.capabilities;

import java.util.List;

import com.picatrix1899.qline.backpacks.utils.WorldBlockPos;

public interface IWorldBlockPosList
{
	List<WorldBlockPos> getPositions();
	
	void addPosition(WorldBlockPos pos);
	
	void removePosition(WorldBlockPos pos);
}
