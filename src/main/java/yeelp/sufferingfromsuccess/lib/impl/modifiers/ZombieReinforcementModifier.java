package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.EntityZombie;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.SFSReflectionHelper;

public final class ZombieReinforcementModifier extends SFSAttributeModifier<EntityZombie> {

	public ZombieReinforcementModifier() {
		super("Reinforcement", SFSAttributeModifier.AttributeModifierUUIDs.REINFORCEMENT, (IAttribute) SFSReflectionHelper.getValue(null, SFSReflectionHelper.ZOMBIE_REINFORCEMENT_ATTRIBUTE), 0, ModifierFunctions.LIMITING_VALUE);
	}

	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.zombies.zombieReinforcementModifier;
	}
	
	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}

}
