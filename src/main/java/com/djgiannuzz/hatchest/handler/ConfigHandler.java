package com.djgiannuzz.hatchest.handler;

import java.io.File;

import com.djgiannuzz.hatchest.HatChest;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.common.config.Configuration;


public class ConfigHandler 
{
	public static Configuration config;
	
	public static boolean balanceEnabled;
	
	public static void init(File configFile)
	{
		
		if(config == null)
		{
			config = new Configuration(configFile);
			loadConfiguration();
		}
	}
	

	private static void loadConfiguration()
	{
		balanceEnabled = config.getBoolean("enableBalance", config.CATEGORY_GENERAL, true, "If set to true you have to balance the chest on your head or the chest will fall.");
		
		if(config.hasChanged())
		{
			config.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equalsIgnoreCase(HatChest.MODID))
		{
			loadConfiguration();
		}
	}
	
}
