package yeelp.sufferingfromsuccess.api.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTTagInt;

public abstract class AbstractIntModifierValue extends ModifierValue<Integer, NBTTagInt> {
	
	protected AbstractIntModifierValue() {
		this.setModifierValue(0);
	}
	
	@Override
	public NBTTagInt serializeNBT() {
		return new NBTTagInt(this.getModifierValue());
	}
	
	@Override
	public void deserializeNBT(NBTTagInt nbt) {
		this.setModifierValue(nbt.getInt());
	}
	
	static <T extends AbstractIntModifierValue> void register(Class<T> clazz, Callable<T> factory) {
		ModifierValue.register(clazz, new ModifierValueStorage<NBTTagInt, T>(), factory);
	}
}
