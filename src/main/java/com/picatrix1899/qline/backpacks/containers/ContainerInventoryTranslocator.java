package com.picatrix1899.qline.backpacks.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerInventoryTranslocator extends Container
{
	private IItemHandler inv;
	private IItemHandler config;
	
	public ContainerInventoryTranslocator(IItemHandler playerInventory, IItemHandler inv, IItemHandler config, EntityPlayer player)
	{
		this.inv = inv;
		this.config = config; 
		
		for (int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new ConfigSlot(this.config, i, 8 + i * 18, 8));
		}

		for (int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new SlotItemHandler(this.inv, i, 8 + i * 18, 8 + 18));
		}
		
        for (int l = 0; l < 3; l++)
        {
            for (int k = 0; k < 9; k++)
            {
                this.addSlotToContainer(new SlotItemHandler(playerInventory, 9 + k + l * 9, 8 + k * 18, 52 + l * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new SlotItemHandler(playerInventory, i, 8 + i * 18, 110));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index)
	{
		final Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			final ItemStack stack = slot.getStack();
			final ItemStack originalStack = stack.copy();
			if(index < 9)
			{
				return ItemStack.EMPTY;
			}
			else if (index > 8 && index < 18)
			{
				if (!mergeItemStack(stack, 9, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!mergeItemStack(stack, 9, 18, false))
			{
				return ItemStack.EMPTY;
			}

			if (stack.getCount() == 0)
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else 
			{
				slot.onSlotChanged();
			}

			return originalStack;
		}

		return ItemStack.EMPTY;
	}
	
	
	@Override
	public boolean canDragIntoSlot(Slot slotIn)
	{
		if(slotIn.getSlotIndex() < 9)
		{
			return false;
		}
		
		return super.canDragIntoSlot(slotIn);
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
	{
		if(slotId < 9 && slotId > -1)
		{
			if ((clickTypeIn == ClickType.PICKUP) && (dragType == 0 || dragType == 1))
			{
				InventoryPlayer inventoryplayer = player.inventory;
				
				Slot slot = this.inventorySlots.get(slotId);

				ItemStack itemstackSlot = slot.getStack();
				ItemStack itemstackCursor = inventoryplayer.getItemStack();
			
				if (!itemstackSlot.isEmpty())
				{
					slot.putStack(ItemStack.EMPTY);
				}
				
				if (itemstackSlot.isEmpty())
				{
					if (!itemstackCursor.isEmpty())
					{
						ItemStack display = itemstackCursor.copy();
						display.setCount(1);
						
						slot.putStack(display);
					}
				}
			
				slot.onSlotChanged();
				
				return ItemStack.EMPTY;
			}
			else if(clickTypeIn == ClickType.PICKUP_ALL)
			{
				return ItemStack.EMPTY;
			}
			

		}

		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}
