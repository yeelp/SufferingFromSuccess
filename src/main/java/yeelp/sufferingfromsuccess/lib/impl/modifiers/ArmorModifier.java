package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class ArmorModifier extends SFSAttributeModifier<EntityLivingBase> {

	public ArmorModifier() {
		super("Armor", SFSAttributeModifier.AttributeModifierUUIDs.ARMOR, SharedMonsterAttributes.ARMOR, 0, ModifierFunctions.LINEAR);
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.general.armorModifier;
	}

	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}

}
