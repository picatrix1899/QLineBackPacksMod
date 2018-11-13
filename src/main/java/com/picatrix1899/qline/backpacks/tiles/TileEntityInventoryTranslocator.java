package com.picatrix1899.qline.backpacks.tiles;

import com.picatrix1899.qline.backpacks.containers.ContainerInventoryTranslocator;
import com.picatrix1899.qline.backpacks.containers.CustomItemHandler;
import com.picatrix1899.qline.backpacks.containers.TypeOnlyCustomItemHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.LockCode;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityInventoryTranslocator extends TileEntity
{

	private ItemStackHandler inv = new CustomItemHandler(this, 9);
	private TypeOnlyCustomItemHandler config = new TypeOnlyCustomItemHandler(this, 9);
	
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.config.deserializeNBT(compound.getCompoundTag("config"));
		this.inv.deserializeNBT(compound.getCompoundTag("inventory"));
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setTag("config", this.config.serializeNBT());
		compound.setTag("inventory", this.inv.serializeNBT());
		
		return compound;
	}
    
    public Container createContainer(EntityPlayer playerIn)
    {
    	IItemHandler playerItemHandler = playerIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
    	
    	return new ContainerInventoryTranslocator(playerItemHandler, this.inv, this.config, playerIn);
    }


    
   public boolean tryAddItemStack(ItemStack stack)
   {
		if(!this.world.isRemote)
		{  	
			for(int i = 0; i < 9; i++)
			{
				ItemStack configStack = this.config.getStackInSlot(i);
				ItemStack localStack = this.inv.getStackInSlot(i);
				
		    	if (configStack.isItemEqual(stack))
		    	{

	        		if(localStack.isEmpty())
	        		{
	        			this.inv.setStackInSlot(i, stack.copy());
			    		return true;
	        		}
	        		else if(localStack.getCount() < 64)
	        		{
		        		if(localStack.getCount() + stack.getCount() > 64)
		        		{
		        			int leftover = localStack.getCount() + stack.getCount() - 64;
		        			
		        			ItemStack newStack = this.inv.getStackInSlot(i).copy();
		        			newStack.setCount(64);
		        			
		        			this.inv.setStackInSlot(i, newStack);
		        			
		        			stack.setCount(leftover);
		        		}
		        		else
		        		{
		        			ItemStack newStack = this.inv.getStackInSlot(i).copy();
		        			newStack.grow(stack.getCount());
		        			this.inv.setStackInSlot(i, newStack);
		        			
				    		return true;
		        		}

	        		}
	        		
	        		markDirty();
		    	}
			}

		}
    	return false;
   }
    
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
    	if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
    	{
    		if(facing == EnumFacing.DOWN)
    		{
        		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv);
    		}

    		return null;
    	}
    	
    	return super.getCapability(capability, facing);
    }
    
    
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
    	if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
    	{
    		return facing == EnumFacing.DOWN;
    	}
    	
    	return super.hasCapability(capability, facing);
    }
}
