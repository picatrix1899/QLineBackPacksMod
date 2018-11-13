package com.picatrix1899.qline.backpacks.items;

import com.picatrix1899.qline.backpacks.IHasModel;
import com.picatrix1899.qline.backpacks.QLineBackpacksMod;
import com.picatrix1899.qline.backpacks.init.ModTabs;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{

	public ItemBase()
	{
		super();
		setCreativeTab(ModTabs.QLINE_BACKPACKS);
	}

	@Override
	public void registerModels()
	{
		QLineBackpacksMod.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
}
