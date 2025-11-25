package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class SwimModifier extends SFSAttributeModifier<EntityLivingBase> {

	public SwimModifier() {
		super("Swim Speed", SFSAttributeModifier.AttributeModifierUUIDs.SWIM, EntityLivingBase.SWIM_SPEED, 2, ModifierFunctions.LIMITING_VALUE.andThen((d) -> d*4));
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.general.swimModifier;
	}

	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}

}
