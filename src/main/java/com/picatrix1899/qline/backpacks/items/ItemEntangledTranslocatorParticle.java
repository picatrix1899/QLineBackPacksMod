package com.picatrix1899.qline.backpacks.items;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.picatrix1899.qline.backpacks.QLineBackpacksMod;
import com.picatrix1899.qline.backpacks.init.ModItems;
import com.picatrix1899.qline.backpacks.init.ModTabs;
import com.picatrix1899.qline.backpacks.tiles.TileEntityInventoryTranslocator;
import com.picatrix1899.qline.backpacks.utils.NBTUtils;
import com.picatrix1899.qline.backpacks.utils.WorldBlockPos;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemEntangledTranslocatorParticle extends ItemBase
{
	public ItemEntangledTranslocatorParticle()
	{
		super();
		setUnlocalizedName("entangledtranslocatorparticle");
		setRegistryName("entangledtranslocatorparticle");
		setMaxStackSize(1);
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ)
	{

        if (!worldIn.isRemote)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityInventoryTranslocator)
            {
            	if(player.isSneaking())
            	{

        			ItemStack stack = player.getHeldItemMainhand();

        			int world = worldIn.provider.getDimension();
        			
        			addPosition(stack, new WorldBlockPos(pos, world));

        			return EnumActionResult.SUCCESS;
            		
            	}
            }
        }
    		
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	public List<WorldBlockPos> getPositions(ItemStack stack)
	{
		ArrayList<WorldBlockPos> out = Lists.newArrayList();

		if(stack.hasTagCompound())
		{
			NBTTagCompound c = stack.getTagCompound();
			
			if(c.hasKey("positions"))
			{
				NBTTagList list = (NBTTagList)c.getTag("positions");
				
				for(int i = 0; i < list.tagCount(); i++)
				{
					out.add(NBTUtils.getWorldBlockPos(list.getCompoundTagAt(i)));
				}
			}
		}
		
		return out;
	}
	
	public void addPosition(ItemStack stack, WorldBlockPos pos)
	{
		if(getPositions(stack).contains(pos)) return;
		
		NBTTagCompound c = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		
		NBTTagList list = c.hasKey("positions") ? (NBTTagList)c.getTag("positions") : new NBTTagList();
		
		list.appendTag(NBTUtils.setWorldBlockPos(pos));
		
		c.setTag("positions", list);
		
		stack.setTagCompound(c);
	}
	
	public void removePosition(ItemStack stack, WorldBlockPos pos)
	{
		int index = -1;
		
		if(stack.hasTagCompound())
		{
			NBTTagCompound c = stack.getTagCompound();
			
			if(c.hasKey("positions"))
			{
				NBTTagList list = (NBTTagList)c.getTag("positions");
				
				for(int i = 0; i < list.tagCount(); i++)
				{
					if(NBTUtils.getWorldBlockPos(list.getCompoundTagAt(i)).equals(pos))
					{
						index = i;
						break;
					}
				}
				
				if(index > -1)
				{
					list.removeTag(index);
				}
			}
		}

	}
}
