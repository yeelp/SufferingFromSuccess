package yeelp.sufferingfromsuccess.api.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yeelp.sufferingfromsuccess.ModConsts;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.handler.Handler;

public final class SFSDeathTrackingData implements ICapabilitySerializable<NBTTagCompound> {

	private int ticksSinceLastDecay = 0;
	private static final String TICKS_KEY = "ticks";
	
	@CapabilityInject(SFSDeathTrackingData.class)
	public static Capability<SFSDeathTrackingData> cap = null;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == cap;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return this.hasCapability(capability, facing) ? cap.cast(this) : null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger(TICKS_KEY, this.ticksSinceLastDecay);
		return ModConfig.trackerType.writeToNBT(tag);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		ModConfig.trackerType.readFromNBT(nbt);
		this.ticksSinceLastDecay = nbt.getInteger(TICKS_KEY);
	}
	
	/**
	 * Keep track of ticks since last decay was done
	 * @return true if decay should be done this tick
	 */
	public boolean tickDecay() {
		return (++this.ticksSinceLastDecay % ModConfig.decaySpeed) == 0;
	}
	
	@CapabilityInject(SFSDeathTrackingData.class)
	public static void onRegister(@SuppressWarnings("unused") Capability<SFSDeathTrackingData> cap) {
		new Handler() {
			private final ResourceLocation loc = new ResourceLocation(ModConsts.MODID, "deathtracking");
			
			@SubscribeEvent
			public void onAttachCapabilities(AttachCapabilitiesEvent<World> evt) {
				if(evt.getObject() instanceof WorldServer && ((WorldServer) evt.getObject()).provider.getDimension() == 0) {
					evt.addCapability(this.loc, new SFSDeathTrackingData());
				}
			}
		}.register();
	}

}
