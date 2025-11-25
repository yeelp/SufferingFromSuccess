package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Sets;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.EntityZombie;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;
import yeelp.sufferingfromsuccess.util.SFSReflectionHelper;

public final class ZombieReinforcementModifier extends SFSAttributeModifier<EntityZombie> {

	private static final Set<UUID> IGNORE = Sets.newHashSet();
	
	public ZombieReinforcementModifier() {
		super("Reinforcement", SFSAttributeModifier.AttributeModifierUUIDs.REINFORCEMENT, (IAttribute) SFSReflectionHelper.getValue(null, SFSReflectionHelper.ZOMBIE_REINFORCEMENT_ATTRIBUTE), 0, ModifierFunctions.LIMITING_VALUE);
	}

	@Override
	public void accept(EntityZombie t, Integer u) {
		if(IGNORE.remove(t.getUniqueID())) {
			return;
		}
		super.accept(t, u);
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.zombies.zombieReinforcementModifier;
	}
	
	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}
	
	public static void blacklistZombie(EntityZombie zombie) {
		IGNORE.add(zombie.getUniqueID());
	}

}
