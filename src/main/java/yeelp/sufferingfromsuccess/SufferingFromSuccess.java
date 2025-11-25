package yeelp.sufferingfromsuccess;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import yeelp.sufferingfromsuccess.api.capability.BlazeFireballRate;
import yeelp.sufferingfromsuccess.api.capability.GuardianAttackSpeedModifier;
import yeelp.sufferingfromsuccess.api.capability.SFSDeathTrackingData;
import yeelp.sufferingfromsuccess.api.capability.SkeletonFireRate;
import yeelp.sufferingfromsuccess.api.capability.TameModifier;
import yeelp.sufferingfromsuccess.api.capability.WitchDrinkSpeedModifier;
import yeelp.sufferingfromsuccess.api.capability.WitchPotionSkills;
import yeelp.sufferingfromsuccess.handler.EntityCapabilityHandler;
import yeelp.sufferingfromsuccess.lib.impl.DeathTrackerType;
import yeelp.sufferingfromsuccess.registries.SFSRegistries;

@Mod(modid = ModConsts.MODID, name = ModConsts.NAME, version = ModConsts.VERSION, certificateFingerprint = "4e4ba57b1ff9109ffd941c9896618fa26eb5e89a")
public class SufferingFromSuccess {

	@SuppressWarnings("static-method")
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SFSLog.setLogger(event.getModLog());
		SFSRegistries.init();
	}

	@SuppressWarnings("static-method")
	@EventHandler
	public void init(@SuppressWarnings("unused") FMLInitializationEvent event) {
		DeathTrackerType.init();
		CapabilityManager.INSTANCE.register(SFSDeathTrackingData.class, new IStorage<SFSDeathTrackingData>() {

			@Override
			public void readNBT(Capability<SFSDeathTrackingData> capability, SFSDeathTrackingData instance, EnumFacing side, NBTBase nbt) {
				instance.deserializeNBT((NBTTagCompound) nbt);
			}

			@Override
			public NBTBase writeNBT(Capability<SFSDeathTrackingData> capability, SFSDeathTrackingData instance, EnumFacing side) {
				return instance.serializeNBT();
			}

		}, SFSDeathTrackingData::new);
		TameModifier.register();
		GuardianAttackSpeedModifier.register();
		SkeletonFireRate.register();
		WitchPotionSkills.register();
		WitchDrinkSpeedModifier.register();
		BlazeFireballRate.register();
		new yeelp.sufferingfromsuccess.handler.EventHandler().register();
		new EntityCapabilityHandler().register();
	}
	
	@SuppressWarnings("static-method")
	@EventHandler
	public void fingerprintViolation(FMLFingerprintViolationEvent event) {
		if(event.isDirectory()) {
			SFSLog.enqueueMsg("Fingerprint doesn't matter in dev environment", SFSLog::debug);
		}
		else {
			SFSLog.enqueueMsg(String.format("The fingerprint found on %s doesn't match!", ModConsts.NAME), SFSLog::warn);
		}
	}
}
