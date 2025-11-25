package yeelp.sufferingfromsuccess.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yeelp.sufferingfromsuccess.api.capability.GuardianAttackSpeedModifier;
import yeelp.sufferingfromsuccess.api.capability.SkeletonFireRate;
import yeelp.sufferingfromsuccess.api.capability.TameModifier;
import yeelp.sufferingfromsuccess.api.capability.WitchDrinkSpeedModifier;
import yeelp.sufferingfromsuccess.api.capability.WitchPotionSkills;

public final class EntityCapabilityHandler implements Handler {

	@SuppressWarnings("static-method")
	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> evt) {
		if(evt.getObject() instanceof EntityTameable) {
			evt.addCapability(TameModifier.LOC, new TameModifier());
		}
		else if (evt.getObject() instanceof EntityGuardian) {
			evt.addCapability(GuardianAttackSpeedModifier.LOC, new GuardianAttackSpeedModifier());
		}
		else if (evt.getObject() instanceof EntityWitch) {
			evt.addCapability(WitchPotionSkills.LOC, new WitchPotionSkills());
			evt.addCapability(WitchDrinkSpeedModifier.LOC, new WitchDrinkSpeedModifier());
		}
		else if (evt.getObject() instanceof AbstractSkeleton) {
			evt.addCapability(SkeletonFireRate.LOC, new SkeletonFireRate());
		}
	}
}
