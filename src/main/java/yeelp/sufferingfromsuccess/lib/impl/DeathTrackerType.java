package yeelp.sufferingfromsuccess.lib.impl;

import java.util.Arrays;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import yeelp.sufferingfromsuccess.lib.IDeathTracker;

public enum DeathTrackerType implements IDeathTracker {
	QUEUE_BASED {
		private IDeathTracker tracker;

		@Override
		protected void initTracker() {
			this.tracker = new QueueBasedDeathTracker();
		}

		@Override
		protected IDeathTracker getDeathTracker() {
			return this.tracker;
		}
		
	},
	COUNT_BASED {
		private IDeathTracker tracker;
		
		@Override
		protected void initTracker() {
			this.tracker = new CountBasedDeathTracker();
		}

		@Override
		protected IDeathTracker getDeathTracker() {
			return this.tracker;
		}

	};
	
	@Override
	public void countDeath(EntityLivingBase deadEntity) {
		this.getDeathTracker().countDeath(deadEntity);
	}
	
	@Override
	public int getDeathCount(EntityLivingBase delegate) {
		return this.getDeathTracker().getDeathCount(delegate);
	}
	
	@Override
	public void decay() {
		this.getDeathTracker().decay();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.getDeathTracker().readFromNBT(tag);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		return this.getDeathTracker().writeToNBT(tag);
	}
	
	protected abstract IDeathTracker getDeathTracker();
	
	protected abstract void initTracker();
	
	public static void init() {
		Arrays.stream(DeathTrackerType.values()).forEach(DeathTrackerType::initTracker);
	}
}
