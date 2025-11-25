package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class KnockbackResistanceModifier extends SFSAttributeModifier<EntityLivingBase> {

	public KnockbackResistanceModifier() {
		super("Knockback Resistance", SFSAttributeModifier.AttributeModifierUUIDs.KNOCKBACK_RESIST, SharedMonsterAttributes.KNOCKBACK_RESISTANCE, 0, ModifierFunctions.LIMITING_VALUE);
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.general.knockbackModifier;
	}

	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}

}
