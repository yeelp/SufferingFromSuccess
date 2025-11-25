package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.Optional;

import net.minecraft.entity.monster.EntityWitch;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.WitchDrinkSpeedModifier;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.RoundingFunction;

public final class WitchDrinkSpeedIncrease extends CapabilityBasedModifier<EntityWitch, Integer, WitchDrinkSpeedModifier> {

	public WitchDrinkSpeedIncrease() {
		super("Drink Speed", new RoundingFunction(ModifierFunctions.LINEAR));
	}
	
	@Override
	protected Optional<WitchDrinkSpeedModifier> getCapability(EntityWitch entity) {
		return SFSAPI.getWitchDrinkSpeedModifier(entity);
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.witches.witchDrinkSpeedModifier;
	}

}
