package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class PickupChance extends AbstractPercentageChanceModifier<EntityMob> {

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.shared.pickupModifier;
	}

	@Override
	protected void performModification(EntityMob entity) {
		if(!(entity instanceof EntityZombie || entity instanceof AbstractSkeleton)) {
			return;
		}
		SFSLog.debug(String.format("%s can now pick up loot.", entity.getName()));
		entity.setCanPickUpLoot(true);
	}

}
