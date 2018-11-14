package com.picatrix1899.qline.backpacks.utils;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public interface ICapabilityAttacher
{
	List<ICapabilityProvider> getProviders(ItemStack stack);
}
