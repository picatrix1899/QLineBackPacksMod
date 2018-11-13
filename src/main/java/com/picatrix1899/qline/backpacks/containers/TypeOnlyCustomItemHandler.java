package com.picatrix1899.qline.backpacks.containers;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemHandlerHelper;

public class TypeOnlyCustomItemHandler extends CustomItemHandler
{

	public TypeOnlyCustomItemHandler(TileEntity tile, int size)
	{
		super(tile, size);
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		this.stacks.set(slot, ItemStack.EMPTY);
		return ItemStack.EMPTY;
	}

	
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
        if (stack.isEmpty())
            return ItemStack.EMPTY;
        
        validateSlotIndex(slot);
        
		if(!simulate)
		{
			ItemStack display = stack.copy();
			display.setCount(1);
			
			this.stacks.set(slot, display);
			
			onContentsChanged(slot);
			
		}
			
		return stack;
	}
	
}
