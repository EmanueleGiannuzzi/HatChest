package com.djgiannuzz.hatchest.utility;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

import com.djgiannuzz.hatchest.init.ModItems;
import com.djgiannuzz.hatchest.items.ItemHatChest;

import cpw.mods.fml.common.FMLCommonHandler;

public class HCUtility 
{
	public static void putChestOnPlayer(EntityPlayer player, TileEntityChest chest, World world, int x, int y, int z)
	{
		ItemStack itemStack = new ItemStack(ModItems.hatChest, 1, 1);
		setItemStackContent(itemStack, chest);
		setItemStackCoords(itemStack, x, y, z);
		int i = EntityLiving.getArmorPosition(itemStack) - 1;
        ItemStack itemstack1 = player.getCurrentArmor(i);
                
        if (itemstack1 == null)
        {
            player.setCurrentItemOrArmor(i + 1, itemStack.copy()); 
//            if(FMLCommonHandler.instance().getEffectiveSide().isClient())
//            	printItemHatChest(player.inventory.armorItemInSlot(3));
            itemStack.stackSize = 0;
        }
        printItemHatChest(itemStack);
//        removeBlock(world, x, y, z);
        
	}
	
	public static void setItemStackContent(ItemStack itemStack, TileEntityChest tileEntity)
	{
		itemStack.setTagCompound(new NBTTagCompound());
		
		tileEntity.writeToNBT(itemStack.stackTagCompound);
//		if(FMLCommonHandler.instance().getEffectiveSide().isClient())
//        	printItemHatChest(itemStack);
				
	}
	
	public static void setItemStackCoords(ItemStack itemStack ,int x, int y, int z)
	{
		int[] coords = {x, y, z};
		itemStack.stackTagCompound.setIntArray("Coords", coords);
	}
	
	public static TileEntityChest getTileEntityContent(ItemStack itemStack)
	{
		TileEntityChest tileEntity = new TileEntityChest();
		tileEntity.readFromNBT(itemStack.getTagCompound());
		return tileEntity;
	}
	
	public static void removeBlock(World world, int x, int y, int z)
	{
		world.removeTileEntity(x, y, z);//To avoid item dropping
		world.setBlockToAir(x, y, z);
	}
	
	public static boolean hasPlayerHatChest(EntityPlayer player)
	{
		if(player.inventory.armorInventory[3] != null)
		{
//			System.out.println(player.inventory.armorInventory[3].getDisplayName());
			return player.inventory.armorInventory[3].getItem().equals(ModItems.hatChest);
		}
		return false;
	}
	
	public static void printItemHatChest(ItemStack stack)
	{
		
		boolean hasItems = false;
		TileEntityChest chest = HCUtility.getTileEntityContent(stack);
		if(chest != null)
		{
//			System.out.println(chest.getSizeInventory());
			for(int i = 0; i<chest.getSizeInventory(); i++)
			{
				ItemStack itemStack = chest.getStackInSlot(i);
				if(itemStack != null)
				{
					System.out.println(itemStack.stackSize + "x" + itemStack.getDisplayName());
					hasItems = true;
				}
				
				
			}
			if(!hasItems)
				System.out.println("EMPTY");
		}
		else
			System.out.println("CHEST NULL!");
	}
}
