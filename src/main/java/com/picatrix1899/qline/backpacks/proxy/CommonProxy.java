package com.picatrix1899.qline.backpacks.proxy;

import com.picatrix1899.qline.backpacks.containers.ContainerInventoryTranslocator;
import com.picatrix1899.qline.backpacks.gui.GuiInventoryTranslocator;
import com.picatrix1899.qline.backpacks.tiles.TileEntityInventoryTranslocator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy implements IGuiHandler
{
    public void preInit(FMLPreInitializationEvent event)
    {	
    	GameRegistry.registerTileEntity(TileEntityInventoryTranslocator.class, new ResourceLocation("qline_backpacks:" + TileEntityInventoryTranslocator.class.getSimpleName()));
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
      
    }
    
    public void registerItemRenderer(Item item, int meta, String id) { }

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tileentity = world.getTileEntity(pos);
		
		switch(ID)
		{
			case 0:
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
			case 0:
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
