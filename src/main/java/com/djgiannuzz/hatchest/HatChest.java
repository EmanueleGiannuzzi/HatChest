package com.djgiannuzz.hatchest;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import com.djgiannuzz.hatchest.gui.hud.HudBalance;
import com.djgiannuzz.hatchest.handler.ConfigHandler;
import com.djgiannuzz.hatchest.handler.HatChestEventHandler;
import com.djgiannuzz.hatchest.init.ModItems;
import com.djgiannuzz.hatchest.proxy.IProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HatChest.MODID, name = HatChest.NAME, version = HatChest.VERSION, guiFactory = "com.djgiannuzz.hatchest.handler.HatChestGuiFactory")
public class HatChest
{
	
    public static final String MODID = "hatchest";
    public static final String NAME = "Hat Chest";
    public static final String VERSION = "1.2";
    
    public static final double THRESHOLD = 20; //THRESHOLD IN DEGREES
    
    @Mod.Instance(MODID)
	public static HatChest instance;
    
    @SidedProxy(clientSide = "com.djgiannuzz.hatchest.proxy.ClientProxy", serverSide = "com.djgiannuzz.hatchest.proxy.ServerProxy")
    public static IProxy proxy;
    	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	ConfigHandler.init(event.getSuggestedConfigurationFile());
    	FMLCommonHandler.instance().bus().register(new ConfigHandler());
    	ModItems.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new HatChestEventHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	if(FMLCommonHandler.instance().getEffectiveSide().isClient())
    		MinecraftForge.EVENT_BUS.register(new HudBalance(Minecraft.getMinecraft()));
    }
}
