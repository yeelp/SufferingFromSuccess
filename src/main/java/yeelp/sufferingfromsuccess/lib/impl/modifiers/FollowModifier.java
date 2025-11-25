package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class FollowModifier extends SFSAttributeModifier<EntityLivingBase> {

	private static final double BONUS_CAP = 500;
	public FollowModifier() {
		super("Follow Range", SFSAttributeModifier.AttributeModifierUUIDs.VISION, SharedMonsterAttributes.FOLLOW_RANGE, 0, ModifierFunctions.POLYNOMIAL.andThen((d) -> Math.min(d, BONUS_CAP)));
	}
	
	@Override
	protected double getConfigValue() {
		return ModConfig.modifiers.general.followModifier;
	}

	@Override
	protected void postApplication(EntityLivingBase t) {
		return;
	}

}
