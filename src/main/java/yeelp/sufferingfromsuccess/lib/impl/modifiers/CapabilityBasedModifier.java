package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.Optional;
import java.util.function.BiFunction;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTPrimitive;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.api.capability.ModifierValue;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;

public abstract class CapabilityBasedModifier<E extends EntityLivingBase, V extends Number, M extends ModifierValue<V, ? extends NBTPrimitive>> implements IDeathCountModifier<E> {
	
	private final BiFunction<Double, Integer, V> mod;
	private final String name;
	
	public CapabilityBasedModifier(String name, BiFunction<Double, Integer, V> mod) {
		this.name = name;
		this.mod = mod;
	}
	
	@Override
	public final void accept(E t, Integer u) {
		this.getCapability(t).ifPresent((cap) -> {
			V value = this.mod.apply(this.getConfigValue(), u);
			SFSLog.debug(String.format("%s %s %f", t.getName(), this.name, value.floatValue()));
			cap.setModifierValue(value);
		});
	}
	
	protected abstract Optional<M> getCapability(E entity);
	
	protected abstract double getConfigValue();
}
