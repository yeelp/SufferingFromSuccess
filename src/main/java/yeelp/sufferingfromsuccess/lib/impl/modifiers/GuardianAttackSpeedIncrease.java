package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.Optional;

import net.minecraft.entity.monster.EntityGuardian;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.GuardianAttackSpeedModifier;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.RoundingFunction;

public final class GuardianAttackSpeedIncrease extends CapabilityBasedModifier<EntityGuardian, Integer, GuardianAttackSpeedModifier> {

	public GuardianAttackSpeedIncrease() {
		super("Beam Attack Speed", new RoundingFunction(ModifierFunctions.LINEAR));
	}

	@Override
	protected Optional<GuardianAttackSpeedModifier> getCapability(EntityGuardian entity) {
		return SFSAPI.getGuardianAttackSpeedModifier(entity);
	}

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.guardians.guardianAttackSpeedModifier;
	}

}
