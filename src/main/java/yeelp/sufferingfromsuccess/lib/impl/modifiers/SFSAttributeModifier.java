package yeelp.sufferingfromsuccess.lib.impl.modifiers;

import java.util.UUID;
import java.util.function.BiFunction;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import yeelp.sufferingfromsuccess.SFSLog;
import yeelp.sufferingfromsuccess.lib.IDeathCountModifier;

public abstract class SFSAttributeModifier<E extends EntityLivingBase> implements IDeathCountModifier<E> {
	
	private final BiFunction<Double, Integer, Double> mod;
	private final String name;
	private final UUID uuid;
	private final IAttribute attributeModified;
	private final int op;
	
	protected interface AttributeModifierUUIDs {
		UUID HP = UUID.fromString("cc3f46ac-3d02-4ccc-a81b-516a3ba01115");
		UUID ARMOR = UUID.fromString("d89a8f6c-91e4-43f4-a6d3-da5f9ce9c3b7");
		UUID TOUGHNESS = UUID.fromString("248f60c9-5577-495f-b616-b4df2512f23d");
		UUID ATTACK = UUID.fromString("3f7f2dd6-160e-4d90-b32e-43790d51b325");
		UUID MOVEMENT = UUID.fromString("f4189173-3b00-4478-8b0a-192fb03198b7");
		UUID KNOCKBACK_RESIST = UUID.fromString("cf8b147e-54c6-4668-a991-cca43022933f");
		UUID VISION = UUID.fromString("6ebb7a57-f2bb-43c9-8a36-22945fc1141d");
		UUID SWIM = UUID.fromString("85c6e0d6-7a92-423a-89a2-d2f6f7e3236d");
		UUID REINFORCEMENT = UUID.fromString("ce6e79c4-1420-4f49-b731-12fa90ad96b9");
	}
	
	protected SFSAttributeModifier(String name, UUID uuid, IAttribute attribute, int op, BiFunction<Double, Integer, Double> mod) {
		this.name = name;
		this.uuid = uuid;
		this.attributeModified = attribute;
		this.mod = mod;
		this.op = op;
	}
	
	@Override
	public void accept(E t, Integer u) {
		IAttributeInstance instance = t.getEntityAttribute(this.attributeModified);
		if(instance == null) {
			return;
		}
		double value = this.mod.apply(this.getConfigValue(), u);
		if(value == 0) {
			return;
		}
		SFSLog.debug(String.format("%s %s %+f", t.getName(), this.name, value));
		AttributeModifier mod = new AttributeModifier(this.uuid, "SFS_"+this.name, value, this.op);
		instance.applyModifier(mod);
		this.postApplication(t);
	}
	
	protected abstract double getConfigValue();
	
	protected abstract void postApplication(EntityLivingBase t);
}
