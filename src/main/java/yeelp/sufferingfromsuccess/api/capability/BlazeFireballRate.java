package yeelp.sufferingfromsuccess.api.capability;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import yeelp.sufferingfromsuccess.ModConsts;

public final class BlazeFireballRate extends AbstractIntModifierValue {

	public static final ResourceLocation LOC = new ResourceLocation(ModConsts.MODID, "blazefireballrate");
	
	private static final int[] ATTACK_STEP_COOLDOWN_MODIFIER = {34, 20, 1, 1, 1};
	private static final int[] ATTACK_STEP_COOLDOWN_BASE = {100, 60, 6, 6, 6};
	
	@CapabilityInject(BlazeFireballRate.class)
	public static Capability<BlazeFireballRate> cap = null;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == cap;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return this.hasCapability(capability, facing) ? cap.cast(this) : null;
	}
	
	public static void register() {
		AbstractIntModifierValue.register(BlazeFireballRate.class, BlazeFireballRate::new);
	}
	
	public int modifyAttackTime(int currAttackTime, int attackStep) {
		return Math.max(1, Math.min(currAttackTime, ATTACK_STEP_COOLDOWN_BASE[attackStep] - ATTACK_STEP_COOLDOWN_MODIFIER[attackStep]*this.getModifierValue()));
	}

}
