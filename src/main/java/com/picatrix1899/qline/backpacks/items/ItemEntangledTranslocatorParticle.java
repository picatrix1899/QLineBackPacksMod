package com.picatrix1899.qline.backpacks.items;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.picatrix1899.qline.backpacks.QLineBackpacksMod;
import com.picatrix1899.qline.backpacks.capabilities.CapabilityWorldBlockPosList;
import com.picatrix1899.qline.backpacks.capabilities.IWorldBlockPosList;
import com.picatrix1899.qline.backpacks.capabilities.WorldBlockPosList;
import com.picatrix1899.qline.backpacks.init.ModItems;
import com.picatrix1899.qline.backpacks.init.ModTabs;
import com.picatrix1899.qline.backpacks.tiles.TileEntityInventoryTranslocator;
import com.picatrix1899.qline.backpacks.utils.ItemStackUtils;
import com.picatrix1899.qline.backpacks.utils.NBTUtils;
import com.picatrix1899.qline.backpacks.utils.WorldBlockPos;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@EventBusSubscriber
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
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
        if (!worldIn.isRemote)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityInventoryTranslocator)
            {
            	if(player.isSneaking())
            	{
        			player.getHeldItemMainhand().getCapability(CapabilityWorldBlockPosList.WORLD_BLOCK_POS_LIST_CAPABILITY, null).addPosition(new WorldBlockPos(pos, worldIn.provider.getDimension()));

        			return EnumActionResult.SUCCESS;
            	}
            }
        }
    		
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event)
	{
		if(event.getObject().getItem() instanceof ItemEntangledTranslocatorParticle)
		{
			event.addCapability(new ResourceLocation(QLineBackpacksMod.MODID), new CapabilityWorldBlockPosList().createProvider(new WorldBlockPosList()));
		}
	}
	
	@SubscribeEvent
	public static void onItemPickUp(EntityItemPickupEvent event)
    {
		if ((event.getItem().getItem().isEmpty()) || (event.getEntityPlayer() == null) || event.getEntityPlayer().world.isRemote) return;

		IItemHandler playerItemHandler = event.getEntityPlayer().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);

		List<ItemStack> particles = ItemStackUtils.getAllItemStacksFromInventory(ModItems.ENTANGELD_TRANSLOCATOR_PARTICLE, playerItemHandler);
		
		boolean updateOnly = false;
		
		if(!particles.isEmpty())
		{
			for(ItemStack particle : particles)
			{
				ArrayList<WorldBlockPos> outdated = Lists.newArrayList();
				
				for(WorldBlockPos pos : particle.getCapability(CapabilityWorldBlockPosList.WORLD_BLOCK_POS_LIST_CAPABILITY, null).getPositions())
				{
					World w = DimensionManager.getWorld(pos.getWorld());
					
					if(w == null)
					{
						outdated.add(pos);
						continue;
					}
					
					TileEntity genericTile = w.getTileEntity(pos.getBlockPos());
					if(genericTile == null || !(genericTile instanceof TileEntityInventoryTranslocator))
					{
						outdated.add(pos);
						continue;
					}
					
					if(!updateOnly)
					{
						TileEntityInventoryTranslocator tile = (TileEntityInventoryTranslocator)genericTile;
						if(tile.tryAddItemStack(event.getItem().getItem()))
						{
							updateOnly = true;
							event.getItem().getItem().setCount(0);
							event.setCanceled(true);
							if (!event.getItem().isSilent())
							{
								event.getItem().world.playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY, event.getEntityPlayer().posZ,
																SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F,
																((event.getItem().world.rand.nextFloat() - event.getItem().world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
							}
							
							((EntityPlayerMP) event.getEntityPlayer()).connection.sendPacket(new SPacketCollectItem(event.getItem().getEntityId(), event.getEntityPlayer().getEntityId(), event.getItem().getItem().getCount()));
						}
					}

				}
				
				for(WorldBlockPos pos : outdated)
					particle.getCapability(CapabilityWorldBlockPosList.WORLD_BLOCK_POS_LIST_CAPABILITY, null).removePosition(pos);
			}
		}
	}
}
