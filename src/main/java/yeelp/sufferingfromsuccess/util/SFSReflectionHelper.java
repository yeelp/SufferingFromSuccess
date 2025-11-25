package yeelp.sufferingfromsuccess.util;

import java.lang.reflect.Field;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public final class SFSReflectionHelper {
	
	public static final Field CREEPER_EXPLOSION_POWER = ObfuscationReflectionHelper.findField(EntityCreeper.class, "field_82226_g");
	public static final Field GHAST_EXPLOSION_POWER = ObfuscationReflectionHelper.findField(EntityGhast.class, "field_92014_j");
	public static final Field ZOMBIE_REINFORCEMENT_ATTRIBUTE = ObfuscationReflectionHelper.findField(EntityZombie.class, "field_110186_bp");
	public static final Field VEX_LIFETIME = ObfuscationReflectionHelper.findField(EntityVex.class, "field_190668_bx");
	public static final Field ENDERMITE_LIFETIME = ObfuscationReflectionHelper.findField(EntityEndermite.class, "field_175497_b");
	
	
	static {
		for(Field f : SFSReflectionHelper.class.getFields()) {
			try {
				Object o = f.get(null);
				if(o instanceof Field) {
					((Field) o).setAccessible(true);
				}
			}
			catch(IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final void setValue(Object owner, Field f, Object value) {
		try {
			f.set(owner, value);
		}
		catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static final Object getValue(Object owner, Field f) {
		try {
			return f.get(owner);
		}
		catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
