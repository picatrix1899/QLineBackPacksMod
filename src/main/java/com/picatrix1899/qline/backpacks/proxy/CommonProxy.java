package com.picatrix1899.qline.backpacks.proxy;

import com.picatrix1899.qline.backpacks.capabilities.CapabilityWorldBlockPosList;
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

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent event)
    {	
    	registerCapabilities();
    	registerTileEntities();
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
      
    }
    
    public void registerItemRenderer(Item item, int meta, String id) { }

    
    protected void registerCapabilities()
    {
    	CapabilityWorldBlockPosList.register();
    }
    
    protected void registerTileEntities()
    {
    	GameRegistry.registerTileEntity(TileEntityInventoryTranslocator.class, new ResourceLocation("qline_backpacks:" + TileEntityInventoryTranslocator.class.getSimpleName()));
    }
}
