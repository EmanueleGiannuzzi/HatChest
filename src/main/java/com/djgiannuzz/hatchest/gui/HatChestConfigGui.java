package com.djgiannuzz.hatchest.gui;


import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.djgiannuzz.hatchest.HatChest;
import com.djgiannuzz.hatchest.handler.ConfigHandler;

import cpw.mods.fml.client.config.GuiConfig;

public class HatChestConfigGui extends GuiConfig 
{
    public HatChestConfigGui(GuiScreen parent) 
    {
        super(parent,
                new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                HatChest.MODID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
	
}
