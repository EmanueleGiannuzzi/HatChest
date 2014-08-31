package com.djgiannuzz.hatchest.utility;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

import com.djgiannuzz.hatchest.init.ModItems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
            itemStack.stackSize = 0;
        }
        
	}
	
	public static void setItemStackContent(ItemStack itemStack, TileEntityChest tileEntity)
	{
		itemStack.setTagCompound(new NBTTagCompound());
		
		tileEntity.writeToNBT(itemStack.stackTagCompound);
				
	}
	
	public static void setItemStackCoords(ItemStack itemStack ,int x, int y, int z)
	{
		int[] coords = {x, y, z};
		itemStack.stackTagCompound.setIntArray("Coords", coords);
	}
	
	public static TileEntityChest getTileEntityFromItemStack(ItemStack itemStack)
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
		if(player.getCurrentArmor(3) != null)
		{
			return player.getCurrentArmor(3).getItem().equals(ModItems.hatChest);
		}
		return false;
	}
	
	public static int[] getSidePositionModifier(int side)
	{
		int[] mod = new int[3];
		switch(side)
		{
			case 0:
			{
				mod[1] = -1;
				break;
			}
			case 1:
			{
				mod[1] = 1;
				break;
			}
			case 2:
			{
				mod[2] = -1;
				break;
			}
			case 3:
			{
				mod[2] = 1;
				break;
			}
			case 4:
			{
				mod[0] = -1;
				break;
			}
			case 5:
			{
				mod[0] = 1;
				break;
			}
		}
		return mod;
	}
	
	public static void removePlayerHatChest(EntityPlayer player)
	{
		if(player.getCurrentArmor(3) != null)
		{
			player.setCurrentItemOrArmor(4, null);
		}
	}
	
	public static void dropChestContent(EntityPlayer player, ItemStack itemStack)
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			TileEntityChest tileEntity = getTileEntityFromItemStack(itemStack);
			
			if (tileEntity != null)
	        {
				double x = player.posX;
				double y = player.posY + 1.5D;
				double z = player.posZ;
				World world = player.worldObj;
				Random random = new Random();
	            
				for (int i1 = 0; i1 < tileEntity.getSizeInventory() + 1; ++i1)
	            {
					ItemStack itemstack;
					if(i1 == tileEntity.getSizeInventory())
						itemstack = new ItemStack(Blocks.chest);
					else
						itemstack = tileEntity.getStackInSlot(i1);
	
	                if (itemstack != null)
	                {
	                    float f = random.nextFloat() * 0.8F + 0.1F;
	                    float f1 = random.nextFloat() * 0.8F + 0.1F;
	                    EntityItem entityitem;
	
	                    for (float f2 = random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
	                    {
	                        int j1 = random.nextInt(21) + 10;
	
	                        if (j1 > itemstack.stackSize)
	                        {
	                            j1 = itemstack.stackSize;
	                        }
	
	                        itemstack.stackSize -= j1;
	                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
	                        float f3 = 0.05F;
	                        entityitem.motionX = (double)((float)random.nextGaussian() * f3);
	                        entityitem.motionY = (double)((float)random.nextGaussian() * f3 + 0.2F);
	                        entityitem.motionZ = (double)((float)random.nextGaussian() * f3);
	                        entityitem.delayBeforeCanPickup = 20;
	                        if (itemstack.hasTagCompound())
	                        {
	                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
	                        }
	                    }
	                }
	            }
	        }
		
		}
	}
}
