package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class MovementModifier extends SFSAttributeModifier<EntityLivingBase> {

	public MovementModifier() {
		super("Movement", SFSAttributeModifier.AttributeModifierUUIDs.MOVEMENT, SharedMonsterAttributes.MOVEMENT_SPEED, 2, ModifierFunctions.LIMITING_VALUE.andThen((d) -> d*2.5));
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.general.movementModifier;
	}
	
	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}

}
