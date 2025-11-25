package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.lang.reflect.Field;
import java.util.function.BiFunction;

import net.minecraft.entity.EntityLivingBase;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;
import yeelp.sufferingfromsuccess.util.SFSReflectionHelper;

abstract class EntityFieldModifier<T extends EntityLivingBase, R extends Number> implements IDeathCountModifier<T> {
	private final Field f;
	private final BiFunction<Double, Integer, R> mod;
	private final String name;
	
	protected EntityFieldModifier(String name, Field f, BiFunction<Double, Integer, R> mod) {
		this.name = name;
		this.f = f;
		this.mod = mod;
	}
	
	@Override
	public void accept(T t, Integer u) {
		R value = this.mod.apply(this.getConfigValue(), u);
		if(value.doubleValue() == 0) {
			return;
		}
		SFSLog.debug(String.format("%s %s %+f", t.getName(), this.name, value.doubleValue()));
		SFSReflectionHelper.setValue(t, this.f,  this.cast(this.getOriginal(t).doubleValue() + value.doubleValue()));
	}
	
	protected abstract double getConfigValue();
	
	protected abstract R getOriginal(T t);
	
	protected abstract R cast(double d);
}