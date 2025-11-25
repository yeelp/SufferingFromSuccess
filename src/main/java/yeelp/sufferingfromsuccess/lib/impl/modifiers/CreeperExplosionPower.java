package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntityCreeper;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.RoundingFunction;
import yeelp.sufferingfromsuccess.util.SFSReflectionHelper;

public final class CreeperExplosionPower extends EntityFieldModifier<EntityCreeper, Integer> {

	public CreeperExplosionPower() {
		super("Explosion Power", SFSReflectionHelper.CREEPER_EXPLOSION_POWER, new RoundingFunction(ModifierFunctions.LOGARITHMIC));
	}

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.creepers.creeperExplosionModifier;
	}
	
	@Override
	protected Integer getOriginal(EntityCreeper t) {
		return (int) SFSReflectionHelper.getValue(t, SFSReflectionHelper.CREEPER_EXPLOSION_POWER);
	}
	
	@Override
	protected Integer cast(double d) {
		return (int) d;
	}

}
