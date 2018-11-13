package com.picatrix1899.qline.backpacks.utils.handlers;

import java.util.ArrayList;

import com.picatrix1899.qline.backpacks.init.ModBlocks;
import com.picatrix1899.qline.backpacks.init.ModItems;
import com.picatrix1899.qline.backpacks.items.ItemEntangledTranslocatorParticle;
import com.picatrix1899.qline.backpacks.tiles.TileEntityInventoryTranslocator;
import com.picatrix1899.qline.backpacks.utils.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item i :  ModItems.ITEMS)
		{
			if(i instanceof IHasModel)
			{
				((IHasModel)i).registerModels();
			}
		}
		
		for(Block b :  ModBlocks.BLOCKS)
		{
			if(b instanceof IHasModel)
			{
				((IHasModel)b).registerModels();
			}
		}
	}
	
}
