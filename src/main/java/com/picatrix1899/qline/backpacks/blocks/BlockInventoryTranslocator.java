package com.picatrix1899.qline.backpacks.blocks;

import java.util.UUID;

import com.picatrix1899.qline.backpacks.QLineBackpacksMod;
import com.picatrix1899.qline.backpacks.init.ModBlocks;
import com.picatrix1899.qline.backpacks.init.ModItems;
import com.picatrix1899.qline.backpacks.items.ItemEntangledTranslocatorParticle;
import com.picatrix1899.qline.backpacks.tiles.TileEntityInventoryTranslocator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockInventoryTranslocator extends BlockBase implements ITileEntityProvider
{
	
	public BlockInventoryTranslocator()
	{
		super(Material.ROCK);
		
		setUnlocalizedName("block_inventorytranslocator");
		setRegistryName("block_inventorytranslocator");
		
		setHardness(30.0F);
		setResistance(2000.0F);
		setSoundType(SoundType.STONE);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityInventoryTranslocator();
	}
	

	
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityInventoryTranslocator)
            {
                playerIn.openGui(QLineBackpacksMod.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
    

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityInventoryTranslocator)
        {
	        IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
	        
	        for (int i = 0; i < handler.getSlots(); ++i)
	        {
	            ItemStack itemstack = handler.getStackInSlot(i);
	
	            if (!itemstack.isEmpty())
	                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemstack);
	        }

	        super.breakBlock(worldIn, pos, state);
        }
    }
}
