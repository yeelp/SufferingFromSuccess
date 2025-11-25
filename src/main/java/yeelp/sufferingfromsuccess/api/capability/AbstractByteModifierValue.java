package yeelp.sufferingfromsuccess.api.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTTagByte;

public abstract class AbstractByteModifierValue extends ModifierValue<Byte, NBTTagByte> {

	protected AbstractByteModifierValue() {
		this.setModifierValue((byte) 0);
	}

	@Override
	public NBTTagByte serializeNBT() {
		return new NBTTagByte(this.getModifierValue());
	}

	@Override
	public void deserializeNBT(NBTTagByte nbt) {
		this.setModifierValue(nbt.getByte());
	}
	
	static <T extends AbstractByteModifierValue> void register(Class<T> clazz, Callable<T> factory) {
		ModifierValue.register(clazz, new ModifierValueStorage<NBTTagByte, T>(), factory);
	}

}
