package com.djgiannuzz.hatchest.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import com.djgiannuzz.hatchest.HatChest;
import com.djgiannuzz.hatchest.init.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHatChest extends ItemArmor
{
	private static final ArmorMaterial HATCHESTARMOR = EnumHelper.addArmorMaterial("HATCHEST", 0, new int[] {2, 0, 0, 0}, 0);
		
	public ItemHatChest() 
	{
		super(HATCHESTARMOR, 0, 0);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setUnlocalizedName("hatChest");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", HatChest.MODID + ":", this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return String.format("item.%s%s", HatChest.MODID + ":", this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	public String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		System.out.println(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
		this.itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == ModItems.hatChest)
		{
			return "hatchest:textures/armor/HatChest.png";
		}
		else
		{
			return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{

		ModelBiped armorModel = new ModelBiped();
		if(itemStack != null)
		{
			if(itemStack.getItem() instanceof ItemHatChest)
				{
				int type = ((ItemArmor)itemStack.getItem()).armorType;
			
				if(type == 0)
				{
//					System.out.println("DAMAGE: " + this.getDamage(itemStack));
					armorModel = HatChest.proxy.getHatArmorModel(this.getDamage(itemStack) == 1);
				}
				else
				{
					armorModel = null;
				}
			}
			
			if(armorModel != null)
			{

				armorModel.bipedHead.showModel = armorSlot == 0;
				armorModel.bipedHeadwear.showModel = armorSlot == 0;
				armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
				armorModel.bipedRightArm.showModel = armorSlot == 1;
				armorModel.bipedLeftArm.showModel = armorSlot == 1;
				armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
				armorModel.isSneak = entityLiving.isSneaking();
				armorModel.isRiding = entityLiving.isRiding();
				armorModel.isChild = entityLiving.isChild();
				armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 :0;
				if(entityLiving instanceof EntityPlayer)
				{
					armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
				}
				return armorModel;
			}
		}
		return null;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer playerEnt)
    {
		System.out.println(itemStack.getItemDamage());
		itemStack.setItemDamage(itemStack.getItemDamage()==1?0:1);
		return itemStack;
    }
	
	@Override
	public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_)
    {
        return false;
    }	
}


