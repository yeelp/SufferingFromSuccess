package yeelp.sufferingfromsuccess.api;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Sets;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import yeelp.sufferingfromsuccess.api.capability.GuardianAttackSpeedModifier;
import yeelp.sufferingfromsuccess.api.capability.ModifierValue;
import yeelp.sufferingfromsuccess.api.capability.SFSDeathTrackingData;
import yeelp.sufferingfromsuccess.api.capability.SkeletonFireRate;
import yeelp.sufferingfromsuccess.api.capability.TameModifier;
import yeelp.sufferingfromsuccess.api.capability.WitchDrinkSpeedModifier;
import yeelp.sufferingfromsuccess.api.capability.WitchPotionSkills;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.registries.SFSRegistries;

public abstract class SFSAPI {
	
	private static final Set<UUID> IGNORE = Sets.newHashSet();

	public static final int getMobDeathCount(EntityLivingBase entity) {
		return ModConfig.trackerType.getDeathCount(entity);
	}
	
	public static final void trackDeath(EntityLivingBase entity) {
		if(entity instanceof EntityPlayer) {
			return;
		}
		ModConfig.trackerType.countDeath(entity);
	}
	
	public static final void applyModifiers(EntityLivingBase entity) {
		if(entity instanceof EntityPlayer || IGNORE.remove(entity.getUniqueID())) {
			//applying blacklist here still allows the entity to receive the processed byte in the event handler so it doesn't get process again.
			return;
		}
		SFSRegistries.applyModifiers(entity, getMobDeathCount(entity));
	}
	
	public static final void blacklistEntity(EntityLivingBase entity) {
		IGNORE.add(entity.getUniqueID());
	}
	
	public static final Optional<SFSDeathTrackingData> getDeathTrackingData(World world) {
		if(world.provider.getDimension() == 0) {
			return Optional.ofNullable(world.getCapability(SFSDeathTrackingData.cap, null));			
		}
		return getDeathTrackingData(world.getMinecraftServer().getWorld(0));
	}
	
	public static Optional<TameModifier> getTameModifier(EntityLivingBase entity) {
		return getCapability(entity, TameModifier.cap);
	}
	
	public static Optional<GuardianAttackSpeedModifier> getGuardianAttackSpeedModifier(EntityLivingBase entity) {
		return getCapability(entity, GuardianAttackSpeedModifier.cap);
	}
	
	public static Optional<WitchPotionSkills> getWitchPotionSkills(EntityLivingBase entity) {
		return getCapability(entity, WitchPotionSkills.cap);
	}
	
	public static Optional<WitchDrinkSpeedModifier> getWitchDrinkSpeedModifier(EntityLivingBase entity) {
		return getCapability(entity, WitchDrinkSpeedModifier.cap);
	}
	
	public static Optional<SkeletonFireRate> getSkeletonFireRate(EntityLivingBase entity) {
		return getCapability(entity, SkeletonFireRate.cap);
	}
	
	private static <T extends Number, NBT extends NBTPrimitive, M extends ModifierValue<T, NBT>> Optional<M>  getCapability(EntityLivingBase entity, Capability<M> cap) {
		return Optional.ofNullable(entity.getCapability(cap, null));
	}
}
