package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class CreeperPoweredChance extends AbstractPercentageChanceModifier<EntityCreeper> {

	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.creepers.creeperPoweredModifier;
	}

	@Override
	protected void performModification(EntityCreeper entity) {
		SFSLog.debug("Creeper will be powered");
		entity.setSilent(true);
		entity.onStruckByLightning(new EntityLightningBolt(entity.world, entity.posX, entity.posY, entity.posZ, true));
		entity.extinguish();
		entity.heal(entity.getMaxHealth());
		entity.setSilent(false);
	}

}
