package yeelp.sufferingfromsuccess.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.monster.EntitySlime;

@Mixin(EntitySlime.class)
public interface MixinASMEntitySlime {

	@Invoker("setSlimeSize")
	public abstract void setSlimeSize(int size, boolean resetHealth);
}
