package yeelp.sufferingfromsuccess.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import yeelp.sufferingfromsuccess.config.ModConfig;
import yeelp.sufferingfromsuccess.lib.impl.modifiers.ZombieReinforcementModifier;

@Mixin(EntityZombie.class)
public abstract class MixinEntityZombie extends EntityMob {

	public MixinEntityZombie(World worldIn) {
		super(worldIn);
	}
	
	@SuppressWarnings("static-method")
	@ModifyArg(method = "attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"), index = 0)
	private Entity blacklistReinforcements(Entity entity) {
		if(!ModConfig.modifiers.zombies.zombieReinforcementsGetReinforcementsBuff) {
			ZombieReinforcementModifier.blacklistZombie((EntityZombie) entity);
		}
		return entity;
	}
}
