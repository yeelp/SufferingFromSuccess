package yeelp.sufferingfromsuccess.lib.impl;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.lib.IDeathTracker;
import yeelp.sufferingfromsuccess.util.IDExtractor;

public class CountBasedDeathTracker implements IDeathTracker {

	private final Map<String, Integer> countMap;
	private final Map<String, Integer> initialDeathCount;
	private final Map<String, Integer> timestampMap;
	private final Set<String> deathRecentlyOccurred;
	
	private static final String MAP_KEY = "map";
	
	public CountBasedDeathTracker() {
		this.countMap = Maps.newHashMap();
		this.initialDeathCount = Maps.newHashMap();
		this.timestampMap = Maps.newHashMap();
		this.deathRecentlyOccurred = Sets.newHashSet();
	}
	
	@Override
	public int getDeathCount(EntityLivingBase delegate) {
		return IDExtractor.getRegistryStringIfNotBlacklisted(delegate).map(this.countMap::get).orElse(0);
	}

	@Override
	public void countDeath(EntityLivingBase deadEntity) {
		IDExtractor.getRegistryStringIfNotBlacklisted(deadEntity).ifPresent((id) -> {
			this.countMap.merge(id, 1, Integer::sum);
			this.deathRecentlyOccurred.add(id);
		});
	}

	@Override
	public void decay() {
		for(String id : this.countMap.keySet()) {
			if(this.deathRecentlyOccurred.remove(id)) {
				this.initialDeathCount.put(id, this.countMap.get(id));
				continue;
			}
			this.countMap.compute(id, (s, i) -> decayModel(this.initialDeathCount.get(s), ModConfig.decayRate, this.timestampMap.merge(s, 1, Integer::sum)));
		}
	}
	
	private static final int decayModel(int a, double k, int t) {
		return (int) Math.round(a*Math.exp(k*t));
	}
	
	private void clear() {
		this.countMap.clear();
		this.initialDeathCount.clear();
		this.timestampMap.clear();
		this.deathRecentlyOccurred.clear();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		NBTTagList lst = new NBTTagList();
		this.countMap.keySet().stream().map((id) -> new NBTEntry(id, this.countMap.get(id), this.initialDeathCount.getOrDefault(id, 0), this.timestampMap.getOrDefault(id, 0), this.deathRecentlyOccurred.contains(id)).toNBT()).forEach(lst::appendTag);
		tag.setTag(MAP_KEY, lst);
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.clear();
		if(!tag.hasKey(MAP_KEY)) {
			SFSLog.warn("World Data Tag has no queue data!");
			return;
		}
		NBTBase nbt = tag.getTag(MAP_KEY);
		if(!(nbt instanceof NBTTagList)) {
			SFSLog.err("incorrect SFS data found! Expected NBTTagList, got: "+nbt.getClass().getSimpleName());
			return;
		}
		NBTTagList lst = (NBTTagList) nbt;
		if(lst.getTagType() != new NBTTagCompound().getId()) {
			SFSLog.err("Expected list of NBT compounds, found other data!?");
			return;
		}
		for(NBTBase id : lst) {
			NBTEntry entry = this.new NBTEntry((NBTTagCompound) id);
			String mobid = entry.getID();
			this.countMap.put(mobid, entry.getCount());
			this.initialDeathCount.put(mobid, entry.getInitialCount());
			this.timestampMap.put(mobid, entry.getTimestamp());
			if(entry.isWasRecent()) {
				this.deathRecentlyOccurred.add(mobid);
			}
		}
	}
	

	private final class NBTEntry {
		private final int count, initialCount, timestamp;
		private final boolean wasRecent;
		private final String id;
		
		private static final String ID = "id", COUNT = "count", INITIAL = "initial", TIMESTAMP = "timestamp", WAS_RECENT = "wasRecent";
		
		public NBTEntry(String id, int count, int initialCount, int timestamp, boolean wasRecent) {
			this.count = count;
			this.initialCount = initialCount;
			this.timestamp = timestamp;
			this.wasRecent = wasRecent;
			this.id = id;
		}
		
		public NBTEntry(NBTTagCompound tag) {
			this(tag.getString(ID), tag.getInteger(COUNT), tag.getInteger(INITIAL), tag.getInteger(INITIAL), tag.getBoolean(WAS_RECENT));
		}
		
		public NBTTagCompound toNBT() {
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setString(ID, this.id);
			nbt.setBoolean(WAS_RECENT, this.wasRecent);
			nbt.setInteger(COUNT, this.count);
			nbt.setInteger(INITIAL, this.initialCount);
			nbt.setInteger(TIMESTAMP, this.timestamp);
			return nbt;
		}
		
		public String getID() {
			return this.id;
		}

		public int getCount() {
			return this.count;
		}

		public int getInitialCount() {
			return this.initialCount;
		}

		public int getTimestamp() {
			return this.timestamp;
		}

		public boolean isWasRecent() {
			return this.wasRecent;
		}
	}
}
