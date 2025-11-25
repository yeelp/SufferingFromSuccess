package yeelp.sufferingfromsuccess.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.api.capability.GuardianAttackSpeedModifier;

@Mixin(EntityGuardian.class)
public abstract class MixinEntityGuardian extends EntityMob {

	public MixinEntityGuardian(World worldIn) {
		super(worldIn);
	}
	
	@Inject(method = "getAttackDuration()I", at = @At("RETURN"), cancellable = true)
	private void alterAttackTime(CallbackInfoReturnable<Integer> cir) {
		SFSAPI.getGuardianAttackSpeedModifier((EntityGuardian) (Object) this).map(GuardianAttackSpeedModifier::getModifierValue).ifPresent(cir::setReturnValue);
	}

}
