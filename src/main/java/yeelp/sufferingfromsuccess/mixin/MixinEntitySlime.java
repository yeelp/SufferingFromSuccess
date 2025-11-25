package yeelp.sufferingfromsuccess.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;
import yeelp.sufferingfromsuccess.api.SFSAPI;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.SlimeSizeModifier;

@Mixin(EntitySlime.class)
public abstract class MixinEntitySlime extends EntityLiving implements IMob {
	
	public MixinEntitySlime(World worldIn) {
		super(worldIn);
	}
	
	@SuppressWarnings("static-method")
	@ModifyArg(method = "setDead()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"), index = 0)
	private Entity blacklistSlime(Entity slime) {
		if(ModConfig.modifiers.slimes.splitSlimesReceiveBuffs) {
			SlimeSizeModifier.blacklistSlime((EntitySlime) slime);			
		}
		else {
			//SlimeSizeModifier doesn't need to blacklist if we are already blacklisting at a general level.
			SFSAPI.blacklistEntity((EntityLivingBase) slime);
		}
		return slime;
	}
}
