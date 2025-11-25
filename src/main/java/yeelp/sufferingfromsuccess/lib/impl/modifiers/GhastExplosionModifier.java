package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntityGhast;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.RoundingFunction;
import yeelp.sufferingfromsuccess.util.SFSReflectionHelper;

public final class GhastExplosionModifier extends EntityFieldModifier<EntityGhast, Integer> {

	public GhastExplosionModifier() {
		super("Fireball Power", SFSReflectionHelper.GHAST_EXPLOSION_POWER, new RoundingFunction(ModifierFunctions.LOGARITHMIC));
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.ghasts.ghastExplosionModifier;
	}

	@Override
	protected Integer getOriginal(EntityGhast t) {
		return t.getFireballStrength();
	}
	
	@Override
	protected Integer cast(double d) {
		return (int) d;
	}
}
