package yeelp.sufferingfromsuccess;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

import org.apache.logging.log4j.Logger;

import net.minecraft.util.Tuple;
import yeelp.sufferingfromsuccess.config.ModConfig;

public final class SFSLog {

	private static Logger logger;
	private static boolean loggerSet = false;
	private static final Queue<Tuple<String, Consumer<String>>> MSGS = new LinkedList<Tuple<String, Consumer<String>>>();
	
	private static final String RAW_PREFIX = String.format("[%s", ModConsts.NAME.toUpperCase());
	private static final String DEBUG_SUFFIX = " (DEBUG)]";
	private static final String NORMAL_SUFFIX = "]";
	
	private static final String DEBUG_LEVEL = RAW_PREFIX + DEBUG_SUFFIX;
	private static final String REGULAR_LEVEL = RAW_PREFIX + NORMAL_SUFFIX;
	
	public static void setLogger(Logger logger) {
		SFSLog.logger = logger;
		loggerSet = logger != null;
		if(loggerSet) {
			MSGS.forEach((t) -> t.getSecond().accept(t.getFirst()));
		}
	}
	
	public static void debug(String msg) {
		if(ModConfig.debug) {
			log(msg, DEBUG_LEVEL, logger::info);			
		}
	}
	
	public static void info(String msg) {
		log(msg, REGULAR_LEVEL, logger::info);
	}
	
	public static void warn(String msg) {
		log(msg, REGULAR_LEVEL, logger::warn);
	}
	
	public static void err(String msg) {
		log(msg, REGULAR_LEVEL, logger::error);
	}
	
	public static void fatal(String msg) {
		log(msg, REGULAR_LEVEL, logger::fatal);
	}
	
	private static void log(String msg, String level, Consumer<String> method) {
		method.accept(formatMsg(msg, level));
	}
	
	public static void enqueueMsg(String msg, Consumer<String> method) {
		if(loggerSet) {
			method.accept(msg);
		}
		else {
			MSGS.add(new Tuple<String, Consumer<String>>(msg, method));
		}
	}
	
	private static String formatMsg(String msg, String level) {
		return String.format("%s %s", level, msg);
	}
}
