package com.djgiannuzz.hatchest.proxy;

import net.minecraft.client.model.ModelBiped;

import com.djgiannuzz.hatchest.models.ModelHat;

public class ClientProxy extends CommonProxy
{
	private static final ModelHat chest = new ModelHat(1.0F, true);
	private static final ModelHat noChest = new ModelHat(1.0F, false);
	
	@Override
	public ModelBiped getHatArmorModel(boolean hasChest)
	{
		if(hasChest)
			return chest;
		else
			return noChest;
	}
}
