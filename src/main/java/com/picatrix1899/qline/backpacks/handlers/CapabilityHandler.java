package com.picatrix1899.qline.backpacks.handlers;

import com.picatrix1899.qline.backpacks.QLineBackpacksMod;
import com.picatrix1899.qline.backpacks.capabilities.CapabilityWorldBlockPosList;
import com.picatrix1899.qline.backpacks.capabilities.WorldBlockPosList;
import com.picatrix1899.qline.backpacks.items.ItemEntangledTranslocatorParticle;
import com.picatrix1899.qline.backpacks.utils.ICapabilityAttacher;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class CapabilityHandler
{
	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event)
	{
		if(event.getObject().getItem() instanceof ICapabilityAttacher)
		{
			ICapabilityAttacher attacher = (ICapabilityAttacher)event.getObject().getItem();
			
			for(ICapabilityProvider provider : attacher.getProviders(event.getObject()))
			{
				event.addCapability(new ResourceLocation(QLineBackpacksMod.MODID), provider);
			}
		}
	}
}
