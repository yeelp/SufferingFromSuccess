package yeelp.sufferingfromsuccess.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityBlaze;
import yeelp.sufferingfromsuccess.api.SFSAPI;

@Mixin(targets = "net.minecraft.entity.monster.EntityBlaze$AIFireballAttack")
public abstract class MixinBlazeFireballAttack extends EntityAIBase {

	@Shadow
	private EntityBlaze blaze;
	
	@Shadow
	private int attackTime;
	
	@Shadow
	private int attackStep;
	
	@Inject(method = "updateTask()V", at = @At("HEAD"))
	private void modifyFireballRate(@SuppressWarnings("unused") CallbackInfo ci) {
		this.attackTime = SFSAPI.getBlazeFireRate(this.blaze).map((bfr) -> bfr.modifyAttackTime(this.attackTime, this.attackStep)).orElse(this.attackTime);
	}
}
