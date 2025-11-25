package yeelp.sufferingfromsuccess.registries;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.AbsorptionModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.ArmorModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.AttackModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.BlazeFireRateModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.CreeperExplosionPower;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.CreeperPoweredChance;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.EndermiteLifetimeModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.FollowModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.GhastExplosionModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.GuardianAttackSpeedIncrease;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.HealthModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.KnockbackResistanceModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.MovementModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.PickupChance;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.SkeletonFireRateModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.SlimeSizeModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.SpiderJockeyChance;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.SwimModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.ToughnessModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.VexesLifetimeModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.VillagerNitwitChance;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.WitchDrinkInvisModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.WitchDrinkRegenModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.WitchDrinkSpeedIncrease;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.WitchUseEnhancedPotionsModifier;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.ZombieBreakDoorsChance;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.ZombieJockeyChance;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.ZombieReinforcementModifier;

public abstract class SFSRegistries {
	
	private SFSRegistries() {
		//no instantiating
	}
	
	private static final Set<ISFSModifierRegistry<? extends EntityLivingBase>> MODIFIERS = Sets.newHashSet();

	public static void applyModifiers(EntityLivingBase target, int deaths) {
		MODIFIERS.forEach((registry) -> {
			if(registry.getEntityClass().isInstance(target)) {
				registry.castAndApplyModifiers(target, deaths);
			}
		});
	}
	
	@SafeVarargs
	public static <T extends EntityLivingBase> void addRegistry(ISFSModifierRegistry<T> registry, IDeathCountModifier<T>...modifiers) {
		for(IDeathCountModifier<T> mod : modifiers) {
			registry.add(mod);
		}
		MODIFIERS.add(registry);
	}
	
	@SafeVarargs
	public static <T extends EntityLivingBase> void addBasicRegistry(Class<T> clazz, IDeathCountModifier<T>...modifiers) {
		addRegistry(ISFSModifierRegistry.createBasicRegistry(clazz), modifiers);
	}
	
	public static void init() {
		addBasicRegistry(EntityLivingBase.class, new HealthModifier(), new ArmorModifier(), new ToughnessModifier(), new AttackModifier(), new MovementModifier(), new KnockbackResistanceModifier(), new FollowModifier(), new SwimModifier(), new AbsorptionModifier());
		addBasicRegistry(EntityCreeper.class, new CreeperPoweredChance(), new CreeperExplosionPower());
		addBasicRegistry(EntityGhast.class, new GhastExplosionModifier());
		addBasicRegistry(EntityZombie.class, new ZombieReinforcementModifier(), new ZombieJockeyChance(), new ZombieBreakDoorsChance());
		addBasicRegistry(EntitySpider.class, new SpiderJockeyChance());
		addBasicRegistry(EntityVex.class, new VexesLifetimeModifier());
		addBasicRegistry(EntityMob.class, new PickupChance());
		addBasicRegistry(EntityLiving.class, new VillagerNitwitChance());
		addBasicRegistry(EntityEndermite.class, new EndermiteLifetimeModifier());
		addBasicRegistry(EntityGuardian.class, new GuardianAttackSpeedIncrease());
		addBasicRegistry(EntitySlime.class, new SlimeSizeModifier());
		addBasicRegistry(EntityWitch.class, new WitchDrinkInvisModifier(), new WitchDrinkRegenModifier(), new WitchDrinkSpeedIncrease(), new WitchUseEnhancedPotionsModifier());
		addBasicRegistry(AbstractSkeleton.class, new SkeletonFireRateModifier());
		addBasicRegistry(EntityBlaze.class, new BlazeFireRateModifier());
	}
}
