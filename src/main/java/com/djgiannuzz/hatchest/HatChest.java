package com.djgiannuzz.hatchest;

import com.djgiannuzz.hatchest.init.ModItems;
import com.djgiannuzz.hatchest.proxy.IProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HatChest.MODID, name = HatChest.NAME, version = HatChest.VERSION)
public class HatChest
{
	
    public static final String MODID = "hatchest";
    public static final String NAME = "Hat Chest Mod";
    public static final String VERSION = "Alpha 0.0.1";
    
    @Mod.Instance(MODID)
	public static HatChest instance;
    
    @SidedProxy(clientSide = "com.djgiannuzz.hatchest.proxy.ClientProxy", serverSide = "com.djgiannuzz.hatchest.proxy.ServerProxy")
    public static IProxy proxy;
    	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	ModItems.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	 
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	 
    }
}
