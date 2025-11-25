package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.Optional;

import net.minecraft.entity.monster.EntityBlaze;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.BlazeFireballRate;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.RoundingFunction;

public final class BlazeFireRateModifier extends CapabilityBasedModifier<EntityBlaze, Integer, BlazeFireballRate> {

	public BlazeFireRateModifier() {
		super("Fireball Rate", new RoundingFunction(ModifierFunctions.LOGARITHMIC));
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.blazes.blazeFireRateModifier;
	}
	
	@Override
	protected Optional<BlazeFireballRate> getCapability(EntityBlaze entity) {
		return SFSAPI.getBlazeFireRate(entity);
	}
}
