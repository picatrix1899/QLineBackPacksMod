package com.picatrix1899.qline.backpacks.gui;

import com.picatrix1899.qline.backpacks.containers.ContainerInventoryTranslocator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiInventoryTranslocator extends GuiContainer
{

	private ResourceLocation GUI_BACKGROUND;
	
	public GuiInventoryTranslocator(Container container)
	{
		super(container);
		
		GUI_BACKGROUND = new ResourceLocation("qline_backpacks:textures/gui/container/inventorytranslocator.png");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_BACKGROUND);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		
	}

}
