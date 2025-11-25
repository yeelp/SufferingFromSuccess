package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntityWitch;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.WitchPotionSkills;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class WitchDrinkInvisModifier extends AbstractPercentageChanceModifier<EntityWitch> {
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.witches.witchDrinkInvisChance;
	}
	
	@Override
	protected void performModification(EntityWitch entity) {
		SFSLog.debug("Witch will drink Invis");
		SFSAPI.getWitchPotionSkills(entity).ifPresent(WitchPotionSkills::allowInvis);
	}

}
