package com.djgiannuzz.hatchest.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.djgiannuzz.hatchest.HatChest;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class ConfigHandler 
{
	public static Configuration config;
	
	public static boolean enableBalance;
	public static boolean enableBalanceHud;
	public static int dropDelay;
	
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
		enableBalance = config.getBoolean("enableBalance", config.CATEGORY_GENERAL, true, "If set to true you have to balance the chest on your head or the chest will fall.");
		enableBalanceHud = config.getBoolean("enableBalanceHud", config.CATEGORY_GENERAL, true, "If set to false the sidebars will not be displayed.");
		dropDelay = config.getInt("chestDropDelay", config.CATEGORY_GENERAL, 15, 0, 200, "How many ticks(20 ticks = 1 second) must pass after the the player is looking at too low(or too high), before the the fall of the chest.");
		
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
