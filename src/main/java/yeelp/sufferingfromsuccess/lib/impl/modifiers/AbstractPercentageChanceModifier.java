package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.SFSRNG;

abstract class AbstractPercentageChanceModifier<E extends EntityLivingBase> implements IDeathCountModifier<E> {

	protected abstract double getConfigValue();
	
	protected abstract void performModification(E entity);
	
	@Override
	public final void accept(E t, Integer u) {
		if(SFSRNG.getRandomDouble() < ModifierFunctions.LIMITING_VALUE.apply(this.getConfigValue(), u)) {
			this.performModification(t);
		}
	}
	
	
}
