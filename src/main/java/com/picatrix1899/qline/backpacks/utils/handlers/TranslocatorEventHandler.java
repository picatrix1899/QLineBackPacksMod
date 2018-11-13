package com.picatrix1899.qline.backpacks.utils.handlers;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.picatrix1899.qline.backpacks.QLineBackpacksMod;
import com.picatrix1899.qline.backpacks.init.ModItems;
import com.picatrix1899.qline.backpacks.items.ItemEntangledTranslocatorParticle;
import com.picatrix1899.qline.backpacks.tiles.TileEntityInventoryTranslocator;
import com.picatrix1899.qline.backpacks.utils.ItemStackUtils;
import com.picatrix1899.qline.backpacks.utils.WorldBlockPos;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@EventBusSubscriber
public class TranslocatorEventHandler
{
	@SubscribeEvent
	public static void onItemPickUp(EntityItemPickupEvent event)
    {
		if ((event.getItem().getItem().isEmpty()) || (event.getEntityPlayer() == null))
		{
			return;
		}

		if(event.getEntityPlayer().world.isRemote)
		{
			return;
		}
		
		ItemStack stack = event.getItem().getItem();

		IItemHandler playerItemHandler = event.getEntityPlayer().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);

		List<ItemStack> particles = ItemStackUtils.getAllItemStacksFromInventory(ModItems.ENTANGELD_TRANSLOCATOR_PARTICLE, playerItemHandler);
		
		boolean updateOnly = false;
		
		if(!particles.isEmpty())
		{
			for(ItemStack particle : particles)
			{

				ItemEntangledTranslocatorParticle particleItem = (ItemEntangledTranslocatorParticle)particle.getItem();
				
				List<WorldBlockPos> positionList = particleItem.getPositions(particle);
				
				ArrayList<WorldBlockPos> outdated = Lists.newArrayList();
				
				for(WorldBlockPos pos : positionList)
				{
					World w = DimensionManager.getWorld(pos.getWorld());
					
					if(w == null)
					{
						outdated.add(pos);
						continue;
					}
					
					TileEntity genericTile = w.getTileEntity(pos.getBlockPos());
					
					System.out.println(genericTile);
					
					if(genericTile == null || !(genericTile instanceof TileEntityInventoryTranslocator))
					{
						outdated.add(pos);
						continue;
					}
					
					if(!updateOnly)
					{
						TileEntityInventoryTranslocator tile = (TileEntityInventoryTranslocator)genericTile;
						if(tile.tryAddItemStack(stack))
						{
							updateOnly = true;
							event.getItem().getItem().setCount(0);
							event.setCanceled(true);
							if (!event.getItem().isSilent()) {
								event.getItem().world.playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY, event.getEntityPlayer().posZ,
										SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F,
										((event.getItem().world.rand.nextFloat() - event.getItem().world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
							}
							((EntityPlayerMP) event.getEntityPlayer()).connection.sendPacket(new SPacketCollectItem(event.getItem().getEntityId(), event.getEntityPlayer().getEntityId(), event.getItem().getItem().getCount()));
						}
					}

				}
				
				for(WorldBlockPos pos : outdated)
					particleItem.removePosition(particle, pos);
			}
		}
	}
	
	@SubscribeEvent
	public static  void onJoin(PlayerEvent.PlayerLoggedInEvent event)
	{
		System.out.println("Logged in");
	}
	
}
