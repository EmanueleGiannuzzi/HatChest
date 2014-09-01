package com.djgiannuzz.hatchest.init;

import net.minecraft.item.Item;

import com.djgiannuzz.hatchest.HatChest;
import com.djgiannuzz.hatchest.items.ItemHatChest;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(HatChest.MODID)
public class ModItems 
{
	public static final Item hatChest = new ItemHatChest();
	
	public static void init()
	{
		GameRegistry.registerItem(hatChest, "hatChest");
	}
}
