package com.djgiannuzz.hatchest.handlers;
import net.minecraft.init.Blocks;
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
			if(event.entityPlayer.getCurrentEquippedItem() == null)
				if(HCUtility.hasPlayerHatChest(event.entityPlayer))
				{
					int[] mod = HCUtility.getSidePositionModifier(event.face);
					if(Blocks.chest.canPlaceBlockAt(event.world, event.x + mod[0], event.y + mod[1], event.z + mod[2]))
					{
						event.world.setBlock(event.x + mod[0], event.y + mod[1], event.z + mod[2], Blocks.chest);
						TileEntityChest chest = HCUtility.getTileEntityFromItemStack(event.entityPlayer.getCurrentArmor(3));
						event.world.setTileEntity(event.x + mod[0], event.y + mod[1], event.z + mod[2], chest);
						HCUtility.removePlayerHatChest(event.entityPlayer);
					}
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