package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.Optional;

import net.minecraft.entity.monster.AbstractSkeleton;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.SkeletonFireRate;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.RoundingFunction;

public final class SkeletonFireRateModifier extends CapabilityBasedModifier<AbstractSkeleton, Integer, SkeletonFireRate> {

	public SkeletonFireRateModifier() {
		super("Fire Rate", new RoundingFunction(ModifierFunctions.LOGARITHMIC));
	}
	
	@Override
	protected Optional<SkeletonFireRate> getCapability(AbstractSkeleton entity) {
		return SFSAPI.getSkeletonFireRate(entity);
	}

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.skeletons.skeletonFireRateModifier;
	}

}
