package com.picatrix1899.qline.backpacks.blocks;

import com.picatrix1899.qline.backpacks.QLineBackpacksMod;
import com.picatrix1899.qline.backpacks.init.ModBlocks;
import com.picatrix1899.qline.backpacks.init.ModItems;
import com.picatrix1899.qline.backpacks.init.ModTabs;
import com.picatrix1899.qline.backpacks.utils.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public abstract class BlockBase extends Block implements IHasModel
{

	public BlockBase(Material materialIn)
	{
		super(materialIn);
		setCreativeTab(ModTabs.QLINE_BACKPACKS);
	}

	@Override
	public void registerModels()
	{
		QLineBackpacksMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		
	}
	
}
