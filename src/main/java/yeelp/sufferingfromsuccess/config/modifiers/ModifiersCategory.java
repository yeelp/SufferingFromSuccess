package yeelp.sufferingfromsuccess.config.modifiers;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;

public class ModifiersCategory {

	@Name("Creepers")
	@Comment("Change modifiers that affect only Creeper based entities")
	public CreepersCategory creepers = new CreepersCategory();

	@Name("Ghasts")
	@Comment("Change modifiers that affect only Ghast based entities")
	public GhastCategory ghasts = new GhastCategory();

	@Name("General")
	@Comment("Change the modifiers that affect all entities")
	public GeneralCategory general = new GeneralCategory();

	@Name("Zombies")
	@Comment("Change the modifiers that affect only Zombie based entities")
	public ZombieCategory zombies = new ZombieCategory();

	@Name("Spiders")
	@Comment("Change the modifiers that affect only Spider based entities")
	public SpiderCategory spiders = new SpiderCategory();

	@Name("Vexes")
	@Comment("Change the modifiers that affect only Vex based entities")
	public VexesCategory vexes = new VexesCategory();

	@Name("Endermites")
	@Comment("Change the modifiers that affect only Endermite based entities")
	public EndermitesCategory endermites = new EndermitesCategory();

	@Name("Guardians")
	@Comment("Change the modifiers that affect only Guardian based entities")
	public GuardianCategory guardians = new GuardianCategory();
	
	@Name("Slimes")
	@Comment("Change the modifiers that affect only Slime based entites")
	public SlimeCategory slimes = new SlimeCategory();
	
	@Name("Witches")
	@Comment("Change the modifiers that affect only Witch based entites")
	public WitchCategory witches = new WitchCategory();
	
	@Name("Skeletons")
	@Comment("Change the modifiers that affect only Skeleton based entities")
	public SkeletonCategory skeletons = new SkeletonCategory();
	
	@Name("Blazes")
	@Comment("Change the modifiers that affect only Blaze based entities")
	public BlazeCategory blazes = new BlazeCategory();

	@Name("Shared")
	@Comment("Change the modifiers that affect certain groups of entities")
	public SharedCategory shared = new SharedCategory();

	public class GeneralCategory {
		@Name("Extra Health")
		@Comment({
				"Multiplier of how much extra health mobs get based off the number that have died already.",
				"The mob receives extra health equal to the number of mobs of this type that died already times this value here."})
		@RangeDouble(min = 0.0)
		public double healthModifier = 3;

		@Name("Extra Armor")
		@Comment({
				"Multiplier of how much extra armor points mobs get based off the number that have died already.",
				"The mob gets extra armor equal to the number of mobs of this type that died already times this value here."})
		@RangeDouble(min = 0.0)
		public double armorModifier = 1;

		@Name("Armor Toughness Modifier")
		@Comment({
				"Determines how much armor toughness mobs get.",
				"The amount of armor toughness they get is equal to d*x where x is the number of times mobs of this type have died and d is this value defined here.",
				"If this value is 0, no bonus will be applied."})
		@RangeDouble(min = 0.0)
		public double toughnessModifier = 0.4;

		@Name("Attack Modifier")
		@Comment({
				"Determines how much extra attack mobs get.",
				"The bonus is equal to d*x where x is the number of times mobs of this type have died and d is the value defined here."})
		@RangeDouble(min = 0.0)
		public double attackModifier = 1;

		@Name("Movement Speed Bonus")
		@Comment({
				"Determins the bonus in movement speed the mob gets.",
				"The bonus is a multiplier that ranges from 1x to 2.5x speed using the formula 2.5*x/(x+d) where x is the number of times the mob has died already and d is the value defined here.",
				"If this value is 0, no bonus will be applied."})
		@RangeDouble(min = 0.0)
		public double movementModifier = 5;

		@Name("Swim Speed Bonus")
		@Comment({
				"Determins the bonus in swim speed the mob gets.",
				"The bonus is a multiplier that ranges from 1x to 4x speed using the formula 4*x/(x+d) where x is the number of times the mob has died already and d is the value defined here.",
				"If this value is 0, no bonus will be applied."})
		@RangeDouble(min = 0.0)
		public double swimModifier = 2.5;

		@Name("Knockback Resistance Bonus")
		@Comment({
				"Determines the bonus to knockback resistance the mob gets.",
				"The bonus is determined by x/(x+d) where x is the number of times the mob died and d is the value defined here.",
				"If this value is 0, no bonus will be applied."})
		@RangeDouble(min = 0.0)
		public double knockbackModifier = 3;

		@Name("Follow Range Bonus")
		@Comment({
				"Determines the bonus to a mob's follow range.",
				"The bonus is determined by d*x^2 where x is the number of times mobs of this type have died and d is this value defined here.",
				"If this value is 0, no bonus will be applied.",
				"This bonus is capped at 200 for performance reasons."})
		@RangeDouble(min = 0.0)
		public double followModifier = 0.5;

		@Name("Absorption Bonus")
		@Comment({
				"Determines the amount of absorption the mob gets.",
				"This is linear and gets this many absorption hearts when spawning times the number of mobs of this type that have died already."})
		@RangeDouble(min = 0.0)
		public double absorptionModifier = 2;
	}

	public class CreepersCategory {
		@Name("Powered Chance")
		@Comment({
				"If not zero, indicates how likely it is (percentage-wise) that a Creeper will spawn as a powered Creeper based on the number of deaths.",
				"The exact formula is x/(x+d) where x is the number of deaths for this mob and d is the value defined here.",
				"(A higher number here indicates a lower percentage chance)"})
		@RangeDouble(min = 0.0)
		public double creeperPoweredModifier = 5.0;

		@Name("Explosion Radius Increase")
		@Comment({
				"A measure of how much stronger creeper explosions get based on the number of deaths.",
				"Creeper explosions grow logaritmically: d*log(x+1), where x is the number of deaths for this mob and d is the value defined here.",
				"The value computed here is rounded to the nearest whole number and added to the original Creeper explosion strength."})
		@RangeDouble(min = 0.0)
		public double creeperExplosionModifier = 3;

	}

	public class GhastCategory {
		@Name("Explosion Radius Increase")
		@Comment({
				"A measure of how much stronger Ghast fireball explosions get based on the number of deaths.",
				"Ghast fireball explosions grow logarithmically: d*log(x+1), where x is the number of deaths for this mob and d is the value defined here.",
				"The value computed here is rounded to the nearest whole number and added to the original Ghast fireball explosion strength."})
		@RangeDouble(min = 0.0)
		public double ghastExplosionModifier = 3;
	}

	public class ZombieCategory {
		@Name("Reinforcement Chance Boost")
		@Comment({
				"The bonus a Zombie receives to its reinforcement chance based on the number of deaths.",
				"The Zombie reinforcement chance is a percentage chance when a zombie gets hit.",
				"The bonus is equal to x/(x+d) where x is the number of deaths for this mob and d is defined here.",
				"(A higher number here indicates a lower percentage chance)"})
		@RangeDouble(min = 0.0)
		public double zombieReinforcementModifier = 6.0;

		@Name("Jockey Chance")
		@Comment({
				"If not zero, specifies the percentage chance that a baby zombie will spawn with a chicken jockey based on the number of deaths.",
				"This is on top of the 5% vanilla chance. The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
				"(A higher number here indicates a lower percentage chance)"})
		@RangeDouble(min = 0.0)
		public double zombieJockeyModifier = 4.0;

		@Name("Break Door Chance")
		@Comment({
				"If not zero, specifies the percentage chance that a zombie will have the ability to break doors based on the number of deaths.",
				"This is on top of the vanilla chance. The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
				"(A higher number here indicates a lower percentage chance)"})
		@RangeDouble(min = 0.0)
		public double zombieBreakDoorModifier = 3.0;
		
		@Name("Reinforcements Receive Reinforcements Buff")
		@Comment("If true, Zombies summoned via reinforcements will also receive a bonus to their reinforcement chance. This can quickly spiral out of control with enough Zombies killed.")
		public boolean zombieReinforcementsGetReinforcementsBuff = false;
	}

	public class SpiderCategory {
		@Name("Jockey Chance")
		@Comment({
				"If not zero, specifies the percentage chance that a spider will spawn with a skeleton rider based on the number of deaths.",
				"This is on top of the standard vanilla chance. The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
				"(A higher number here indicates a lower percentage chance)"})
		@RangeDouble(min = 0.0)
		public double spiderJockeyModifier = 5.0;
	}

	public class VexesCategory {
		@Name("Lifetime Duration Increase")
		@Comment({
				"Determines how much longer Vexes live when summoned with a finite lifetime (like from Evokers) based on the number of deaths.",
				"The bonus lifetime is equal to d*x^2 ticks where x is the number of times this mob type has died and d is defined here."})
		@RangeDouble(min = 0.0)
		public double vexLifetimeModifier = 10.0;
	}

	public class EndermitesCategory {
		@Name("Lifetime Duration Increase")
		@Comment({
				"Determines how much longer Endermites live before despawning if they are set to eventually despawn based on the number of deaths.",
				"The bonus lifetime is equal to d*x^2 ticks where x is the number of times this mob type has died and d is defined here."})
		@RangeDouble(min = 0.0)
		public double endermiteLifetimeModifier = 20.0;
	}

	public class GuardianCategory {
		@Name("Guardian Attack Speed Modifier")
		@Comment({
				"Determines how much faster Guardians deal damage based on the number of deaths.",
				"The reduction in attack speed equals d*x where x is the number of time this mob type has died and d is the value defined here.",
				"The value here is rounded and subtracted from the Guardian's base attack speed (80), and capped below at 1."})
		@RangeDouble(min = 0.0)
		public double guardianAttackSpeedModifier = 10.0;
	}

	public class SlimeCategory {
		@Name("Slime Size Increase")
		@Comment({
				"Determines how much larger Slimes are based on the number of deaths.",
				"Slimes that spawn as a result of splitting don't receive this benefit.",
				"The increase is logarithmic: d*log(x+1), where x is the number of times this mob type has died and d is defined here.",
				"The result is rounded to the nearest whole number and added to this slime's current size."})
		@RangeDouble(min = 0.0)
		public double slimeSizeModifier = 2.0;
		
		@Name("Split Slimes Receive Buffs")
		@Comment("If true, slimes that split from a slime dying receive buffs like any other mob, minus the Slime Size Increase.")
		public boolean splitSlimesReceiveBuffs = true;
	}
	
	public class WitchCategory {
		@Name("Invis Potion Chance")
		@Comment({
			"If not zero, specifies the chance that a Witch will spawn with the ability to drink Invisibility potions based on the number of deaths, which they will drink any time they aren't invisible and wanting to drink a different potion",
			"The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
			"(A higher number here indicates a lower percentage chance)"
		})
		@RangeDouble(min = 0.0)
		public double witchDrinkInvisChance = 3.0;
		
		@Name("Regen Potion Chance")
		@Comment({
			"If not zero, specifies the chance that a Witch will spawn with the ability to drink Regeneration potions based on the number of deaths, which they will drink any time they don't already have the potion active and wanting to drink a different potion",
			"The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
			"(A higher number here indicates a lower percentage chance)",
			"Witches will drink Regeneration potions over Invisibility potions if they have the ability to drink both."
		})
		@RangeDouble(min = 0.0)
		public double witchDrinkRegenChance = 5.0;
		
		@Name("Drink Speed Modifier")
		@Comment({
			"Specifies how fast Witches drink potions based on the number of deaths.",
			"The exact formula is d*x where x is the number of times this mob type has died and d is the value defined here.",
			"This is deducted from the base drinking duration, and capped below at 1 tick."
		})
		@RangeDouble(min = 0.0)
		public double witchDrinkSpeedModifier = 10;
		
		@Name("Use Enhanced Potions Chance")
		@Comment({
			"If not zero, specifies the chance that a Witch will spawn with the ability to use stronger potions (both those they drink and those they throw) based on the number of deaths; using either extended or enhanced potions if available.",
			"The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
			"(A higher number here indicates a lower percentage chance)"
		})
		@RangeDouble(min = 0.0)
		public double witchEnhancedPotionsModifier = 4.0;
	}
	
	public class SkeletonCategory {
		@Name("Fire Rate Increase")
		@Comment({
			"Specifies how fast Skeletons shoot based on the number of deaths.",
			"The exact formula is d*log(x+1) where x is the number of times this mob type has died and d is the value defined here.",
			"The result is subtracted from the skeleton's base fire rate, to a minimum of 1 tick."
		})
		@RangeDouble(min = 0.0)
		public double skeletonFireRateModifier = 5.0;
	}
	
	public class BlazeCategory {
		@Name("Blaze Fire Rate")
		@Comment({
			"Specifies how fast Blazes shoot based on the number of deaths.",
			"The exact formula is d*log(x+1) where x is the number of times this mob type has died and d is the value defined here.",
			"The result is used as the base fire rate for the Blaze, to a minimum of 1 tick, and modified depending on where in their attack cycle they are."
		})
		@RangeDouble(min = 0.0)
		public double blazeFireRateModifier = 0.6;
	}

	public class SharedCategory {
		@Name("Pickup Chance")
		@Comment({
				"If not zero, specifies the percentage chance that a zombie or skeleton will spawn with the ability to pick up loot.",
				"This is on top of the standard vanilla chance. The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
				"(A higher number here indicates a lower percentage chance)"})
		@RangeDouble(min = 0.0)
		public double pickupModifier = 3.0;

		@Name("Tame Chance")
		@Comment({
				"If not zero, specifies the percentage chance that taming a tamable mob will fail based on the number of deaths.",
				"This is on top of the standard vanilla chance. The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
				"(A higher number here indicates a lower percentage chance)"})
		@RangeDouble(min = 0.0)
		public double tameModifier = 3.0;

		@Name("Nitwit Chance")
		@Comment({
				"If not zero, specifies the percentage chance villagers and zombie villagers spawn as nitwits based on the number of deaths.",
				"This is on top of the standard vanilla chance. The exact formula is x/(x+d) where x is the number of times this mob type has died and d is the value defined here.",
				"(A higher number here indicates a lower percentage chance)"})
		@RangeDouble(min = 0.0)
		public double villagerNitwitModifier = 4.0;
	}
}
