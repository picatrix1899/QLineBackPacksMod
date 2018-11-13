package com.picatrix1899.qline.backpacks.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public abstract class CreativeTabBase extends CreativeTabs
{

	private ItemStack tabIcon;
	private String background;
	
	public CreativeTabBase(String label, ItemStack icon, String background)
	{
		super(label);
		this.tabIcon = icon;
		this.background = background;
		
	 	if(!this.background.isEmpty()) setBackgroundImageName(this.background);
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return this.tabIcon;
	}

}
