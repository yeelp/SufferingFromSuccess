package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.Optional;

import net.minecraft.entity.passive.EntityTameable;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.TameModifier;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class TameFailChance extends CapabilityBasedModifier<EntityTameable, Float, TameModifier> {

	public TameFailChance() {
		super("Tame Failure Rate", ModifierFunctions.LIMITING_VALUE.andThen(Double::floatValue));
	}
	
	@Override
	protected Optional<TameModifier> getCapability(EntityTameable entity) {
		return SFSAPI.getTameModifier(entity);
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.shared.tameModifier;
	}
}
