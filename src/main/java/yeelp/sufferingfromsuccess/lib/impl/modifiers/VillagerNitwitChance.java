package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class VillagerNitwitChance extends AbstractPercentageChanceModifier<EntityLiving> {

	private static final int NITWIT_ID = 5;
	@SuppressWarnings("deprecation")
	private static final VillagerProfession NITWIT = VillagerRegistry.getById(NITWIT_ID);
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.shared.villagerNitwitModifier;
	}
	
	@Override
	protected void performModification(EntityLiving entity) {
		if(entity instanceof EntityVillager) {
			SFSLog.debug(String.format("%s will be a Nitwit", entity.getName()));
			((EntityVillager) entity).setProfession(NITWIT);
		}
		else if(entity instanceof EntityZombieVillager) {
			SFSLog.debug(String.format("%s will be a Nitwit", entity.getName()));
			((EntityZombieVillager) entity).setProfession(NITWIT_ID);
		}
	}
}
