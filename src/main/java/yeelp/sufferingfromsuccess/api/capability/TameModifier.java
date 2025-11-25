package yeelp.sufferingfromsuccess.api.capability;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import yeelp.sufferingfromsuccess.ModConsts;

public final class TameModifier extends AbstractFloatModifierValue {

	public static final ResourceLocation LOC = new ResourceLocation(ModConsts.MODID, "tamemodifier");
	
	@CapabilityInject(TameModifier.class)
	public static Capability<TameModifier> cap = null;
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return this.hasCapability(capability, facing) ? cap.cast(this) : null;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == cap;
	}
	
	public static void register() {
		AbstractFloatModifierValue.register(TameModifier.class, TameModifier::new);
	}
}
