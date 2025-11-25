package yeelp.sufferingfromsuccess.lib;

import java.util.function.BiConsumer;

import net.minecraft.entity.EntityLivingBase;

/**
 * Applies an effect to an entity based on the number of times similar entities have died.
 * @author Yeelp
 *
 */
public interface IDeathCountModifier<E extends EntityLivingBase> extends BiConsumer<E, Integer> {
	//empty
}
