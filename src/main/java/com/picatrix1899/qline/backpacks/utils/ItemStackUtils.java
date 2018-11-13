package com.picatrix1899.qline.backpacks.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ItemStackUtils
{
	
	public static List<ItemStack> getAllItemStacksFromInventory(Item item, IItemHandler handler)
	{
		List<ItemStack> out = new ArrayList<ItemStack>();
		
		for(int i = 0; i < handler.getSlots(); i++)
		{
			if(handler.getStackInSlot(i).getItem().equals(item) || handler.getStackInSlot(i).getItem() == item)
			{
				out.add(handler.getStackInSlot(i));
			}
		}
		
		return out;
	}
}
