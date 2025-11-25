package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntityVex;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.RoundingFunction;
import yeelp.sufferingfromsuccess.util.SFSReflectionHelper;

public final class VexesLifetimeModifier extends EntityFieldModifier<EntityVex, Integer> {
	
	public VexesLifetimeModifier() {
		super("Lifetime", SFSReflectionHelper.VEX_LIFETIME, new RoundingFunction(ModifierFunctions.POLYNOMIAL));
	}

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.vexes.vexLifetimeModifier;
	}
	
	@Override
	protected Integer cast(double d) {
		return (int) d;
	}
	
	@Override
	protected Integer getOriginal(EntityVex t) {
		return (int) SFSReflectionHelper.getValue(t, SFSReflectionHelper.VEX_LIFETIME);
	}

}
