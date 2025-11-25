package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.monster.EntityZombie;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class ZombieBreakDoorsChance extends AbstractPercentageChanceModifier<EntityZombie> {

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.zombies.zombieBreakDoorModifier;
	}

	@Override
	protected void performModification(EntityZombie entity) {
		SFSLog.debug("Zombie will break doors");
		entity.setBreakDoorsAItask(true);
	}

}
