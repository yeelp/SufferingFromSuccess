package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Sets;

import net.minecraft.entity.monster.EntitySlime;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;
import yeelp.sufferingfromsuccess.mixin.MixinASMEntitySlime;
import yeelp.sufferingfromsuccess.util.ModifierFunctions;

public final class SlimeSizeModifier implements IDeathCountModifier<EntitySlime> {

	private static final Set<UUID> IGNORE = Sets.newHashSet();
	@Override
	public void accept(EntitySlime t, Integer u) {
		if(IGNORE.remove(t.getUniqueID())) {
			return;
		}
		int value = (int) Math.round(ModifierFunctions.LOGARITHMIC.apply(ModConfig.modifiers.slimes.slimeSizeModifier, u));
		SFSLog.debug(String.format("Slime Size %+d for %s", value, t.getName()));
		MixinASMEntitySlime asmSlime = (MixinASMEntitySlime) t;
		asmSlime.setSlimeSize(t.getSlimeSize() + value, true);
	}
	
	public static void blacklistSlime(EntitySlime slime) {
		IGNORE.add(slime.getUniqueID());
	}

}
