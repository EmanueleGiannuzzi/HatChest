package com.djgiannuzz.hatchest.handlers;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

import com.djgiannuzz.hatchest.utility.HCUtility;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
* Name and cast of this class are irrelevant
*/
public class HatChestEventHandler
{

	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void playerRightClick(PlayerInteractEvent event)
	{

//		System.out.println((event.world.isRemote?"CLIENT":"SERVER"));
		if(event.action.equals(Action.RIGHT_CLICK_BLOCK) && event.entityPlayer.isSneaking())
		{
			if(HCUtility.hasPlayerHatChest(event.entityPlayer))
			{
				//TODO
			}
			else
			{
				TileEntity tileEntity = event.world.getTileEntity(event.x, event.y, event.z);
				if(tileEntity != null && tileEntity instanceof TileEntityChest)
				{
					TileEntityChest chest = (TileEntityChest)tileEntity;
					HCUtility.putChestOnPlayer(event.entityPlayer, chest, event.world, event.x, event.y, event.z);
					
	//				event.setCanceled(true);
					
				}
			}
		}
	}
	
}