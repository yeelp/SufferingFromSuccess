package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntityWitch;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.WitchPotionSkills;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class WitchUseEnhancedPotionsModifier extends AbstractPercentageChanceModifier<EntityWitch> {

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.witches.witchEnhancedPotionsModifier;
	}
	
	@Override
	protected void performModification(EntityWitch entity) {
		SFSLog.debug("Witch will use enhanced potions");
		SFSAPI.getWitchPotionSkills(entity).ifPresent(WitchPotionSkills::allowEnhancedPotions);
	}
}
