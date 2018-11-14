package com.picatrix1899.qline.backpacks.items;

import com.picatrix1899.qline.backpacks.QLineBackpacksMod;
import com.picatrix1899.qline.backpacks.init.ModItems;
import com.picatrix1899.qline.backpacks.init.ModTabs;
import com.picatrix1899.qline.backpacks.utils.IHasModel;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{

	public ItemBase()
	{
		super();
		ModItems.ITEMS.add(this);
		setCreativeTab(ModTabs.QLINE_BACKPACKS);
	}

	@Override
	public void registerModels()
	{
		QLineBackpacksMod.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
}
