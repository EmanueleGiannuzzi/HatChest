package com.djgiannuzz.hatchest.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHat extends ModelBiped
{
	
    /** The chest lid in the chest's model. */
    public ModelRenderer chestLid ;
    /** The model of the bottom of the chest. */
    public ModelRenderer chestBelow;
    /** The chest's knob in the chest model. */
    public ModelRenderer chestKnob;
    	
	public ModelHat(float f, boolean hasChest)
	{
		super(f, 0, 64, 64);
		textureWidth = 64;
		textureHeight = 64;
				
		if(hasChest)
		{
			float offsetX = -8F;
			float offsetY = -24.0F;
			float offsetZ = -8F;
			
			this.chestLid = (new ModelRenderer(this, 0, 16)).setTextureSize(textureWidth, textureHeight);
			this.chestLid.addBox(0.0F + offsetX, -5.0F + offsetY, -14.0F + offsetZ, 14, 5, 14, 0.0F);
	        this.chestLid.rotationPointX = 1.0F;
	        this.chestLid.rotationPointY = 7.0F;
	        this.chestLid.rotationPointZ = 15.0F;
	        this.chestKnob = (new ModelRenderer(this, 0, 16)).setTextureSize(textureWidth, textureHeight);
	        this.chestKnob.addBox(-1.0F + offsetX, -2.0F + offsetY, -15.0F + offsetZ, 2, 4, 1, 0.0F);
	        this.chestKnob.rotationPointX = 8.0F;
	        this.chestKnob.rotationPointY = 7.0F;
	        this.chestKnob.rotationPointZ = 15.0F;
	        this.chestBelow = (new ModelRenderer(this, 0, 35)).setTextureSize(textureWidth, textureHeight);
	        this.chestBelow.addBox(0.0F + offsetX, 0.0F + offsetY, 0.0F + offsetZ, 14, 10, 14, 0.0F);
	        this.chestBelow.rotationPointX = 1.0F;
	        this.chestBelow.rotationPointY = 6.0F;
	        this.chestBelow.rotationPointZ = 1.0F;
	        
			this.bipedHead.addChild(chestLid);
			this.bipedHead.addChild(chestKnob);
			this.bipedHead.addChild(chestBelow);
		}
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity,  f,  f1,  f2,  f3,  f4,  f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}