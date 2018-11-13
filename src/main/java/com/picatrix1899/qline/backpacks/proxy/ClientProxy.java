package com.picatrix1899.qline.backpacks.proxy;

import com.picatrix1899.qline.backpacks.capabilities.CapabilityWorldBlockPosList;
import com.picatrix1899.qline.backpacks.tiles.TileEntityInventoryTranslocator;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
    	super.preInit(event);
    }

    public void init(FMLInitializationEvent event)
    {
      
    }

    public void postInit(FMLPostInitializationEvent event)
    {
      
    }
    
    public void registerItemRenderer(Item item, int meta, String id)
    {
    	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
}
