package yeelp.sufferingfromsuccess.api.capability;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import yeelp.sufferingfromsuccess.ModConsts;

public final class GuardianAttackSpeedModifier extends AbstractIntModifierValue {

	public static final ResourceLocation LOC = new ResourceLocation(ModConsts.MODID, "guardianattackspeedmodifier");
	
	@CapabilityInject(GuardianAttackSpeedModifier.class)
	public static Capability<GuardianAttackSpeedModifier> cap = null;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == cap;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return this.hasCapability(capability, facing) ? cap.cast(this) : null;
	}
	
	public static void register() {
		AbstractIntModifierValue.register(GuardianAttackSpeedModifier.class, GuardianAttackSpeedModifier::new);
	}

}
