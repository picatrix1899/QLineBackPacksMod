package com.picatrix1899.qline.backpacks;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import org.apache.logging.log4j.Logger;

import com.picatrix1899.qline.backpacks.proxy.CommonProxy;

@Mod(modid = QLineBackpacksMod.MODID, name = QLineBackpacksMod.NAME, version = QLineBackpacksMod.VERSION)
public class QLineBackpacksMod
{
    public static final String MODID = "qline_backpacks";
    public static final String NAME = "QLine Backpacks";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @SidedProxy(modId=MODID, clientSide="com.picatrix1899.qline.backpacks.proxy.ClientProxy", serverSide="com.picatrix1899.qline.backpacks.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance
    public static QLineBackpacksMod INSTANCE;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        this.proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
      this.proxy.init(event);
      
      NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
      this.proxy.postInit(event);
    }
}
