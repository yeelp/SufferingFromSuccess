package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class AbsorptionModifier implements IDeathCountModifier<EntityLivingBase> {
	@Override
	public void accept(EntityLivingBase t, Integer u) {
		float value = ModifierFunctions.LINEAR.apply(ModConfig.modifiers.general.absorptionModifier, u).floatValue();
		SFSLog.debug(String.format("%s gets %f absorption", t.getName(), value));
		t.setAbsorptionAmount(value);
	}
}
