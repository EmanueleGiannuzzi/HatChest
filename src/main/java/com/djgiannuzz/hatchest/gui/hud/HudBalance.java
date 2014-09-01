package com.djgiannuzz.hatchest.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import com.djgiannuzz.hatchest.HatChest;
import com.djgiannuzz.hatchest.handler.ConfigHandler;
import com.djgiannuzz.hatchest.items.ItemHatChest;
import com.djgiannuzz.hatchest.utility.HCUtility;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HudBalance extends Gui
{
	ResourceLocation texture = new ResourceLocation(HatChest.MODID, "textures/gui/hud/GradientBar.png");
	private Minecraft mc;
	
	public HudBalance(Minecraft mc)
	{
		super();
	    this.mc = mc;
	}
		
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE || !HCUtility.hasPlayerHatChest(this.mc.thePlayer) || !ConfigHandler.enableBalance || !ConfigHandler.enableBalanceHud)
		{      
		  return;
		}
		
		int xPos = 10;
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		int yPos = (scaledresolution.getScaledHeight() / 2) - 40;
		
		int zeroPos = yPos + 40;
		int yaw = (int)(mc.thePlayer.rotationPitch*2);
		yaw = (int)(yaw<(HatChest.THRESHOLD * 2)?yaw>-(HatChest.THRESHOLD * 2)?yaw:-(HatChest.THRESHOLD*2):(HatChest.THRESHOLD*2));
		int drop = ((ItemHatChest)this.mc.thePlayer.getCurrentArmor(3).getItem()).tickBeforeDropClient;
		int posDrop = (drop*79) / ConfigHandler.dropDelay;
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);      
	    this.mc.renderEngine.bindTexture(texture);
	
	    this.drawTexturedModalRect(xPos, yPos, 0, 0, 8, 81);
	    this.drawTexturedModalRect(xPos - 3, zeroPos + yaw, 8, 0, 14, 1);
	    if(ConfigHandler.dropDelay != 0)
	    {
		    this.drawTexturedModalRect(xPos + 14, yPos, 8, 0, 8, 81);
		    this.drawTexturedModalRect(xPos + 15, yPos + 1 + posDrop, 16, 1 + posDrop, 6, 79 - posDrop);
	    }
	}
}
