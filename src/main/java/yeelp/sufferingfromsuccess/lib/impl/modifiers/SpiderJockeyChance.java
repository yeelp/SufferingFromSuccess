package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class SpiderJockeyChance extends AbstractPercentageChanceModifier<EntitySpider> {

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.spiders.spiderJockeyModifier;
	}
	
	@Override
	protected void performModification(EntitySpider entity) {
		if(!entity.getPassengers().isEmpty()) {
			return;
		}
		SFSLog.debug(String.format("%s now has Skeleton rider", entity.getName()));
		EntitySkeleton skeleton = new EntitySkeleton(entity.world);
		skeleton.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, 0.0f);
		skeleton.onInitialSpawn(entity.world.getDifficultyForLocation(entity.getPosition()), null);
		entity.world.spawnEntity(skeleton);
		skeleton.startRiding(entity);
	}
}
