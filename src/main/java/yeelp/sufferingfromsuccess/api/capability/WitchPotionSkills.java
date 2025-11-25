package yeelp.sufferingfromsuccess.api.capability;

import java.util.Collection;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;

import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import yeelp.sufferingfromsuccess.ModConsts;
import yeelp.sufferingfromsuccess.util.SFSRNG;

public final class WitchPotionSkills extends AbstractByteModifierValue {
	
	public static final ResourceLocation LOC = new ResourceLocation(ModConsts.MODID, "witchpotionskills");
	
	private static final byte REGEN_FLAG = (byte) 0x1;
	private static final byte INVIS_FLAG = (byte) 0x10;
	private static final byte ENHANCE_FLAG = (byte) 0x100;
	
	private static final Multimap<PotionType, PotionType> ENHANCE_POTION_MAP = MultimapBuilder.hashKeys().arrayListValues().build();
	
	static {
		ENHANCE_POTION_MAP.put(PotionTypes.HEALING, PotionTypes.STRONG_HEALING);
		ENHANCE_POTION_MAP.put(PotionTypes.SWIFTNESS, PotionTypes.LONG_SWIFTNESS);
		ENHANCE_POTION_MAP.put(PotionTypes.SWIFTNESS, PotionTypes.STRONG_SWIFTNESS);
		ENHANCE_POTION_MAP.put(PotionTypes.FIRE_RESISTANCE, PotionTypes.LONG_FIRE_RESISTANCE);
		ENHANCE_POTION_MAP.put(PotionTypes.INVISIBILITY, PotionTypes.LONG_INVISIBILITY);
		ENHANCE_POTION_MAP.put(PotionTypes.WATER_BREATHING, PotionTypes.LONG_WATER_BREATHING);
		ENHANCE_POTION_MAP.put(PotionTypes.REGENERATION, PotionTypes.LONG_REGENERATION);
		ENHANCE_POTION_MAP.put(PotionTypes.REGENERATION, PotionTypes.STRONG_REGENERATION);
		ENHANCE_POTION_MAP.put(PotionTypes.HARMING, PotionTypes.STRONG_HARMING);
		ENHANCE_POTION_MAP.put(PotionTypes.POISON, PotionTypes.STRONG_POISON);
		ENHANCE_POTION_MAP.put(PotionTypes.POISON, PotionTypes.LONG_POISON);
		ENHANCE_POTION_MAP.put(PotionTypes.SLOWNESS, PotionTypes.LONG_SLOWNESS);
		ENHANCE_POTION_MAP.put(PotionTypes.WEAKNESS, PotionTypes.LONG_WEAKNESS);
	}
	
	@CapabilityInject(WitchPotionSkills.class)
	public static Capability<WitchPotionSkills> cap = null;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == cap;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return this.hasCapability(capability, facing) ? cap.cast(this) : null;
	}
	
	public static void register() {
		AbstractByteModifierValue.register(WitchPotionSkills.class, WitchPotionSkills::new);
	}
	
	public void allowInvis() {
		this.allow(INVIS_FLAG);
	}
	
	public void allowRegen() {
		this.allow(REGEN_FLAG);
	}
	
	public void allowEnhancedPotions() {
		this.allow(ENHANCE_FLAG);
	}
	
	public boolean isInvisAllowed() {
		return this.isAllowed(INVIS_FLAG);
	}
	
	public boolean isRegenAllowed() {
		return this.isAllowed(REGEN_FLAG);
	}
	
	public boolean areEnhancedPotionsAllowed() {
		return this.isAllowed(ENHANCE_FLAG);
	}
	
	public static PotionType enhancePotion(PotionType type) {
		Collection<PotionType> types = ENHANCE_POTION_MAP.get(type);
		int choice = SFSRNG.getRandomInt(types.size());
		return types.stream().skip(choice).findFirst().orElse(type);
	}
	
	private void allow(byte flag) {
		this.setModifierValue((byte) (this.getModifierValue() | flag));
	}
	
	private boolean isAllowed(byte flag) {
		return (this.getModifierValue() & flag) > 0;
	}

}
