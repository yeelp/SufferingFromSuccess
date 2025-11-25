package yeelp.sufferingfromsuccess.util;

import java.util.Arrays;
import java.util.Optional;

import com.google.common.base.Functions;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class IDExtractor {

	private IDExtractor() {
		//no instantiating!
	}
	
	public static Optional<String> getRegistryString(EntityLivingBase entity) {
		//must map one after the other instead of compose in case EntityList::getKey returns null
		return Optional.ofNullable(entity).map(EntityList::getKey).map(Functions.toStringFunction());
	}
	
	public static Optional<String> getRegistryStringIfNotBlacklisted(EntityLivingBase entity) {
		return getRegistryString(entity).filter((id) -> Arrays.stream(ModConfig.blacklist).noneMatch(id::equals));
	}
}
