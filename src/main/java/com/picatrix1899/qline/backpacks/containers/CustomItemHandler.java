package com.picatrix1899.qline.backpacks.containers;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class CustomItemHandler extends ItemStackHandler
{
	private TileEntity tile;
	
	public CustomItemHandler(TileEntity tile, int size)
	{
		super(size);
		this.tile = tile;
	}
	
	@Override
	protected void onContentsChanged(int slot)
	{
		this.tile.markDirty();
	}
}
