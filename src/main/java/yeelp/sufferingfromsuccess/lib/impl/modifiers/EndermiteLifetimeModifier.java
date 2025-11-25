package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntityEndermite;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.RoundingFunction;
import yeelp.sufferingfromsuccess.util.SFSReflectionHelper;

public final class EndermiteLifetimeModifier extends EntityFieldModifier<EntityEndermite, Integer> {

	public EndermiteLifetimeModifier() {
		//need to make modification negative to subtract, since field counts up and despawns at 2400.
		super("Lifetime", SFSReflectionHelper.ENDERMITE_LIFETIME, new RoundingFunction(ModifierFunctions.POLYNOMIAL).andThen((i) -> i*-1));
	}
	
	@Override
	public void accept(EntityEndermite t, Integer u) {
		if(t.isNoDespawnRequired()) {
			return;
		}
		super.accept(t, u);
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.endermites.endermiteLifetimeModifier;
	}

	@Override
	protected Integer getOriginal(EntityEndermite t) {
		return (int) SFSReflectionHelper.getValue(t, SFSReflectionHelper.ENDERMITE_LIFETIME);
	}

	@Override
	protected Integer cast(double d) {
		return (int) d;
	}

}
