package yeelp.sufferingfromsuccess.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionType;
import net.minecraft.world.World;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.WitchPotionSkills;
import yeelp.sufferingfromsuccess.util.SFSRNG;

@Mixin(EntityWitch.class)
public abstract class MixinEntityWitch extends EntityMob implements IRangedAttackMob {
	
	public MixinEntityWitch(World worldIn) {
		super(worldIn);
	}
	
	@Shadow
	private int potionUseTimer;

	@Unique
	private static final double POTION_CHANCE = 0.15;
	
	@ModifyVariable(method = "onLivingUpdate()V", at = @At("STORE"), ordinal = 0)
	private PotionType setPotion(PotionType type) {
		EntityWitch witch = (EntityWitch) (Object) this;
		return SFSAPI.getWitchPotionSkills(witch).map((skills) -> {
			if(skills.isRegenAllowed() && SFSRNG.getRandomDouble() < POTION_CHANCE && !witch.isPotionActive(MobEffects.REGENERATION) && (witch.getAttackTarget() == null || witch.getAttackTarget().getDistanceSq(witch) > 121)) {
				return PotionTypes.REGENERATION;
			}
			else if(skills.isInvisAllowed() && SFSRNG.getRandomDouble() < POTION_CHANCE && !witch.isPotionActive(MobEffects.INVISIBILITY) && witch.getAttackTarget() == null) {
				return PotionTypes.INVISIBILITY;
			}
			return type;
		}).orElse(type);
	}
	
	@Redirect(method = "onLivingUpdate()V", slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/potion/PotionUtils;addPotionToItemStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/potion/PotionType;)Lnet/minecraft/item/ItemStack;")), at = @At(value = "FIELD", target = "Lnet/minecraft/entity/monster/EntityWitch;potionUseTimer:I", opcode = Opcodes.PUTFIELD))
	private void changeDrinkSpeed(EntityWitch witch, int speed) {
		this.potionUseTimer = SFSAPI.getWitchDrinkSpeedModifier(witch).map((speedMod) -> Math.max(1, speed - speedMod.getModifierValue())).orElse(speed);
	}
	
	@ModifyArg(method = "onLivingUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/potion/PotionUtils;addPotionToItemStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/potion/PotionType;)Lnet/minecraft/item/ItemStack;"), index = 1)
	private PotionType enhancePotion(PotionType type) {
		EntityWitch witch = (EntityWitch) (Object) this;
		if(SFSAPI.getWitchPotionSkills(witch).filter(WitchPotionSkills::areEnhancedPotionsAllowed).isPresent()) {
			return WitchPotionSkills.enhancePotion(type);
		}
		return type;
	}
	
	@ModifyArg(method = "attackEntityWithRangedAttack(Lnet/minecraft/entity/EntityLivingBase;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/potion/PotionUtils;addPotionToItemStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/potion/PotionType;)Lnet/minecraft/item/ItemStack;"), index = 1)
	private PotionType enhanceSplashPotion(PotionType type) {
		EntityWitch witch = (EntityWitch) (Object) this;
		if(SFSAPI.getWitchPotionSkills(witch).filter(WitchPotionSkills::areEnhancedPotionsAllowed).isPresent()) {
			return WitchPotionSkills.enhancePotion(type);
		}
		return type;
	}

}
