package yeelp.sufferingfromsuccess.registries;

import java.util.Set;
import java.util.function.BiConsumer;

import net.minecraft.entity.EntityLivingBase;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;
import yeelp.sufferingfromsuccess.registries.impl.BasicSFSModifierRegistry;

public interface ISFSModifierRegistry<T extends EntityLivingBase> extends Set<IDeathCountModifier<T>>, BiConsumer<T, Integer> {

	/**
	 * Get the entity class to cast to when applying modifiers.
	 * @return The entity class
	 */
	Class<T> getEntityClass();
	
	@Override
	default void accept(T t, Integer u) {
		this.forEach((mod) -> mod.accept(t, u));
	}
	
	default void castAndApplyModifiers(EntityLivingBase target, int deaths) {
		T casted = this.getEntityClass().cast(target);
		this.forEach((mod) -> mod.accept(casted, deaths));
	}
	
	static <E extends EntityLivingBase> ISFSModifierRegistry<E> createBasicRegistry(Class<E> clazz) {
		return new BasicSFSModifierRegistry<E>(clazz);
	}
}
