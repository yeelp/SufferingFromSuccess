package yeelp.sufferingfromsuccess.api.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTTagFloat;

public abstract class AbstractFloatModifierValue extends ModifierValue<Float, NBTTagFloat> {

	protected AbstractFloatModifierValue() {
		this.setModifierValue(0.0f);
	}
	
	@Override
	public NBTTagFloat serializeNBT() {
		return new NBTTagFloat(this.getModifierValue());
	}

	@Override
	public void deserializeNBT(NBTTagFloat nbt) {
		this.setModifierValue(nbt.getFloat());
	}
	
	static <T extends AbstractFloatModifierValue> void register(Class<T> clazz, Callable<T> factory) {
		ModifierValue.register(clazz, new ModifierValueStorage<NBTTagFloat, T>(), factory);
	}

}
