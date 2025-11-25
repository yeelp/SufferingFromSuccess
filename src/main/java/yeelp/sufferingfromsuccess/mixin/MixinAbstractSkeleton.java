package yeelp.sufferingfromsuccess.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import yeelp.sufferingfromsuccess.api.SFSAPI;

@Mixin(AbstractSkeleton.class)
public abstract class MixinAbstractSkeleton extends EntityMob implements IRangedAttackMob {
	
	public MixinAbstractSkeleton(World worldIn) {
		super(worldIn);
	}
	
	@ModifyArg(method = "setCombatTask()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/EntityAIAttackRangedBow;setAttackCooldown(I)V"), index = 0)
	private int changeFireRate(int fireRate) {
		AbstractSkeleton skeleton = (AbstractSkeleton) (Object) this;
		return SFSAPI.getSkeletonFireRate(skeleton).map((fr) -> Math.max(1, fireRate - fr.getModifierValue())).orElse(fireRate);
	}

}
