package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class ZombieJockeyChance extends AbstractPercentageChanceModifier<EntityZombie> {

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.zombies.zombieJockeyModifier;
	}

	@Override
	protected void performModification(EntityZombie entity) {
		if(!entity.isChild() || entity.isRiding()) {
			return;
		}
		SFSLog.debug(String.format("%s is now a Chicken Jockey", entity.getName()));
		EntityChicken chicken = new EntityChicken(entity.world);
		chicken.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, 0.0f);
		chicken.onInitialSpawn(entity.world.getDifficultyForLocation(entity.getPosition()), null);
		chicken.setChickenJockey(true);
		entity.world.spawnEntity(chicken);
		entity.startRiding(chicken);
	}
}
