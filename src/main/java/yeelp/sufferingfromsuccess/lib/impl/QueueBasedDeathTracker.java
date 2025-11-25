package yeelp.sufferingfromsuccess.lib.impl;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.lib.IDeathTracker;
import yeelp.sufferingfromsuccess.util.FixedCapacityQueue;
import yeelp.sufferingfromsuccess.util.IDExtractor;

public final class QueueBasedDeathTracker implements IDeathTracker {
	
	private static final String QUEUE_KEY = "queue";
	private final FixedCapacityQueue<String> deaths;
	
	public QueueBasedDeathTracker() {
		this.deaths = new FixedCapacityQueue<String>(ModConfig.queueSize);
	}
	
	@Override
	public int getDeathCount(EntityLivingBase delegate) {
		return IDExtractor.getRegistryStringIfNotBlacklisted(delegate).map((id) -> this.deaths.stream().filter(id::equals).count()).orElse(0L).intValue();
	}

	@Override
	public void countDeath(EntityLivingBase deadEntity) {
		IDExtractor.getRegistryStringIfNotBlacklisted(deadEntity).ifPresent(this.deaths::add);
	}

	@Override
	public void decay() {
		return;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.deaths.clear();
		if(!tag.hasKey(QUEUE_KEY)) {
			SFSLog.warn("World Data Tag has no queue data!");
			return;
		}
		NBTBase nbt = tag.getTag(QUEUE_KEY);
		if(!(nbt instanceof NBTTagList)) {
			SFSLog.err("incorrect SFS data found! Expected NBTTagList, got: "+nbt.getClass().getSimpleName());
			return;
		}
		NBTTagList lst = (NBTTagList) nbt;
		if(lst.getTagType() != new NBTTagString().getId()) {
			SFSLog.err("Expected list of NBT strings, found other data!?");
			return;
		}
		for(NBTBase id : lst) {
			this.deaths.add(((NBTTagString) id).getString());
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		NBTTagList lst = new NBTTagList();
		this.deaths.forEach((id) -> {
			lst.appendTag(new NBTTagString(id));
		});
		tag.setTag(QUEUE_KEY, lst);
		return tag;
	}

}
