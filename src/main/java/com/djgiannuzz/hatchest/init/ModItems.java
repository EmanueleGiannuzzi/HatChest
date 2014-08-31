package com.djgiannuzz.hatchest.init;

import com.djgiannuzz.hatchest.items.ItemHatChest;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.item.Item;

public class ModItems 
{
	public static final Item hatChest = new ItemHatChest();
	
	public static void init()
	{
		GameRegistry.registerItem(hatChest, "hatChest");
	}
}
