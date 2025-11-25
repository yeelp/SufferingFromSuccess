package yeelp.sufferingfromsuccess.handler;

import net.minecraftforge.common.MinecraftForge;

public interface Handler {

	default void register() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
