package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class ToughnessModifier extends SFSAttributeModifier<EntityLivingBase> {

	public ToughnessModifier() {
		super("Toughness", SFSAttributeModifier.AttributeModifierUUIDs.TOUGHNESS, SharedMonsterAttributes.ARMOR_TOUGHNESS, 0, ModifierFunctions.POLYNOMIAL);
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.general.toughnessModifier;
	}

	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}

}
