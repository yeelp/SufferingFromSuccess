package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class AttackModifier extends SFSAttributeModifier<EntityLivingBase> {

	public AttackModifier() {
		super("Attack", SFSAttributeModifier.AttributeModifierUUIDs.ATTACK, SharedMonsterAttributes.ATTACK_DAMAGE, 0, ModifierFunctions.LINEAR);
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.general.attackModifier;
	}

	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}

}
