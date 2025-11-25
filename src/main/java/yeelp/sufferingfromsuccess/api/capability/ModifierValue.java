package yeelp.sufferingfromsuccess.api.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public abstract class ModifierValue<V extends Number, NBT extends NBTPrimitive> implements ICapabilitySerializable<NBT> {

	private V value;
	
	public V getModifierValue() {
		return this.value;
	}
	
	public void setModifierValue(V value) {
		this.value = value;
	}
	
	static final class ModifierValueStorage<D extends NBTPrimitive, T extends ModifierValue<? extends Number, D>> implements IStorage<T> {

		@Override
		public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side) {
			return instance.serializeNBT();
		}

		@SuppressWarnings("unchecked")
		@Override
		public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt) {
			instance.deserializeNBT((D) nbt);
		}
	}
	
	static <T> void register(Class<T> clazz, IStorage<T> storage, Callable<T> factory) {
		CapabilityManager.INSTANCE.register(clazz, storage, factory);
	}
}
