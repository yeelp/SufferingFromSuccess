package yeelp.sufferingfromsuccess.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yeelp.sufferingfromsuccess.ModConsts;
import yeelp.sufferingfromsuccess.config.modifiers.ModifiersCategory;
import yeelp.sufferingfromsuccess.lib.impl.DeathTrackerType;

@Config(modid = ModConsts.MODID)
public class ModConfig {

	@Name("Debug Mode")
	@Comment("Enable SFS debug mode")
	public static boolean debug = false;
	
	@Name("Death Tracker Type")
	@Comment({"How should Suffering From Success track entity deaths?",
		"COUNT_BASED - Suffering From Success tracks individual deaths per mob id. The count will decay over time using exponential decay and the specified Decay Rate and Decay Speed.",
		"QUEUE_BASED - Suffering From Success will keep track of the last N many deaths, where N is the Death Queue Capacity."})
	public static DeathTrackerType trackerType = DeathTrackerType.COUNT_BASED;
	
	@Name("Death Queue Capacity")
	@Comment("How many entity deaths in total should a queue based death tracker keep track of at once?")
	public static int queueSize = 30;
	
	@Name("Death Count Decay Rate")
	@Comment({"The rate of decay for an individual count based death tracker.", 
		"Closer to zero means a slower rate of decay (recommended). Zero means no decay.",
		"The count based death tracker decreases the mob death count using an exponential decay model: a*e^(rt), where:",
		"    a is the initial death count of the mob type. Killing a mob of this type resets this.",
		"    e is Euler's number (~2.718)",
		"    r is the decay rate defined here.",
		"    t is the number of times decay has occurred (including this time). Killing a mob of this type resets this to 1."})
	@RangeDouble(max = 0)
	public static double decayRate = -0.1;
	
	@Name("Death Count Decay Speed")
	@Comment({"How often decay occurs in the individual count based death tracker.",
	          "The default value is once a minute.",
	          "Longer decay speeds is probably better."})
	@RangeInt(min = 1)
	public static int decaySpeed = 1200;
	
	@Name("Blacklisted Mobs")
	@Comment({"Mobs listed here won't be tracked by the death tracker and won't receive bonuses on spawning",
		"You really only want to put mobs here that find themselves regularly dying from unfortunate spawning conditions (bats flying into lava, squids getting beached, etc.)",
		"This is only really important if using a Queue Based Death Tracker so the queue of deaths doesn't get filled up by these mobs dying randomly."})
	public static String[] blacklist = {
			"minecraft:bat",
			"minecraft:squid"
	};
	
	@Name("Modifiers")
	@Comment("Configure how the mobs are affected by deaths.")
	public static final ModifiersCategory modifiers = new ModifiersCategory();
	
	@Mod.EventBusSubscriber(modid = ModConsts.MODID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChange(final ConfigChangedEvent.OnConfigChangedEvent evt) {
			if(evt.getModID().equals(ModConsts.MODID)) {
				ConfigManager.sync(ModConsts.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
