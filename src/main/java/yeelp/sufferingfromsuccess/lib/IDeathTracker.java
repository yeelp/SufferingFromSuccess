package yeelp.sufferingfromsuccess.lib;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Tracks deaths
 * @author Yeelp
 *
 */
public interface IDeathTracker {

	/**
	 * Get the death count for entities that match the delegate
	 * @param delegate The entity that represents the death count to retrieve.
	 * @return The amount of times that entity has died in this tracker.
	 */
	int getDeathCount(EntityLivingBase delegate);
	
	/**
	 * Count this entity as having died for this tracker.
	 * @param deadEntity the entity that died.
	 */
	void countDeath(EntityLivingBase deadEntity);
	
	/**
	 * Decay the death tracker; such that deaths from a while ago no longer influence the death count.
	 */
	void decay();
	
	/**
	 * Read from an NBT compound tag for saved data
	 * @param tag the compound tag to read
	 */
	void readFromNBT(NBTTagCompound tag);
	
	/**
	 * Write the state of this DeathTracker to an NBTCompoundTag
	 * @param tag the tag to write to
	 * @return the saved data written to the compound tag.
	 */
	NBTTagCompound writeToNBT(NBTTagCompound tag);
}
