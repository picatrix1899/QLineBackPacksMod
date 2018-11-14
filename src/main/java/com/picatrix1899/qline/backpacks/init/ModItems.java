package com.picatrix1899.qline.backpacks.init;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.picatrix1899.qline.backpacks.items.ItemEntangledTranslocatorParticle;
import com.picatrix1899.qline.backpacks.items.ItemFlowerPouch;
import com.picatrix1899.qline.backpacks.items.ItemFoodPouch;
import com.picatrix1899.qline.backpacks.items.ItemTreePouch;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ModItems
{
	public static final List<Item> ITEMS = Lists.newArrayList();

	public static final ItemEntangledTranslocatorParticle ENTANGELD_TRANSLOCATOR_PARTICLE = new ItemEntangledTranslocatorParticle();

	public static final ItemFlowerPouch ITEM_FLOWER_POUCH = new ItemFlowerPouch();
	public static final ItemFoodPouch ITEM_FOOD_POUCH = new ItemFoodPouch();
	public static final ItemTreePouch ITEM_TREE_POUCH = new ItemTreePouch();
}
