package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class HealthModifier extends SFSAttributeModifier<EntityLivingBase> {


	public HealthModifier() {
		super("Health", SFSAttributeModifier.AttributeModifierUUIDs.HP, SharedMonsterAttributes.MAX_HEALTH, 0, ModifierFunctions.LINEAR);
	}

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.general.healthModifier;
	}

	@Override
	protected void postApplication(EntityLivingBase t) {
		t.heal(t.getMaxHealth());
	}

}
