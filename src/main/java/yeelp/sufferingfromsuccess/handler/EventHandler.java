package yeelp.sufferingfromsuccess.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.TameModifier;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.util.SFSRNG;

public final class EventHandler implements Handler {

	private static final String KEY = "sufferingfromsuccess:processed";
	private static final NBTTagByte PROCESSED = new NBTTagByte((byte) 1);
	
	@SuppressWarnings("static-method")
	@SubscribeEvent
	public void onDeath(LivingDeathEvent evt) {
		if(evt.getEntity().getEntityWorld().isRemote) {
			return;
		}
		SFSAPI.trackDeath(evt.getEntityLiving());
		SFSLog.debug(String.format("%s has died. Current death count: %d.", evt.getEntityLiving().getName(), SFSAPI.getMobDeathCount(evt.getEntityLiving())));
	}
	
	@SuppressWarnings("static-method")
	@SubscribeEvent
	public void onSpawn(EntityJoinWorldEvent evt) {
		Entity entity = evt.getEntity();
		NBTTagCompound data = entity.getEntityData();
		if(!data.hasKey(KEY) && !entity.getEntityWorld().isRemote && entity instanceof EntityLivingBase) {
			SFSAPI.applyModifiers((EntityLivingBase) entity);
			data.setTag(KEY, PROCESSED);
		}
	}
	
	@SuppressWarnings("static-method")
	@SubscribeEvent
	public void onTick(WorldTickEvent evt) {
		if(evt.world.isRemote) {
			return;
		}
		SFSAPI.getDeathTrackingData(evt.world).ifPresent((data) -> {
			if(data.tickDecay()) {
				SFSLog.debug("Performing decay");
				ModConfig.trackerType.decay();
			}
		});
	}
	
	@SuppressWarnings("static-method")
	@SubscribeEvent
	public void onTame(AnimalTameEvent evt) {
		SFSAPI.getTameModifier(evt.getEntityLiving()).map(TameModifier::getModifierValue).filter((f) -> SFSRNG.getRandomDouble() < f).ifPresent((f) -> {
			evt.setCanceled(true);
			SFSLog.debug("Taming Failed");
		});
	}
}
