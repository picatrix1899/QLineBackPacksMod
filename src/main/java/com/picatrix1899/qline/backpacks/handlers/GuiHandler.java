package com.picatrix1899.qline.backpacks.handlers;

import com.picatrix1899.qline.backpacks.gui.GuiInventoryTranslocator;
import com.picatrix1899.qline.backpacks.init.ModGuiContainer;
import com.picatrix1899.qline.backpacks.tiles.TileEntityInventoryTranslocator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tileentity = world.getTileEntity(pos);
		
		switch(ID)
		{
			case ModGuiContainer.INVENTORY_TRANSLOCATOR:
			{
				if ((tileentity instanceof TileEntityInventoryTranslocator))
				{
					return ((TileEntityInventoryTranslocator)tileentity).createContainer(player);
				}
				break;
			}
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tileentity = world.getTileEntity(pos);
		
		switch(ID)
		{
			case ModGuiContainer.INVENTORY_TRANSLOCATOR:
			{
				if ((tileentity instanceof TileEntityInventoryTranslocator))
				{
					return new GuiInventoryTranslocator(((TileEntityInventoryTranslocator)tileentity).createContainer(player));
				}
				break;
			}
		}

		return null;
	}
}
