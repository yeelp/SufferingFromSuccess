package yeelp.sufferingfromsuccess.registries.impl;

import java.util.HashSet;

import net.minecraft.entity.EntityLivingBase;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;
import yeelp.sufferingfromsuccess.registries.ISFSModifierRegistry;

public class BasicSFSModifierRegistry<T extends EntityLivingBase> extends HashSet<IDeathCountModifier<T>> implements ISFSModifierRegistry<T> {
	private final Class<T> clazz;
	
	public BasicSFSModifierRegistry(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public Class<T> getEntityClass() {
		return this.clazz;
	}
	
}