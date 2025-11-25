package yeelp.sufferingfromsuccess.util;

import java.util.Random;

public final class SFSRNG {
	
	private static final Random rand = new Random(System.currentTimeMillis());
	
	private SFSRNG() {
		//no instantiating
	}

	public static double getRandomDouble() {
		return rand.nextDouble();
	}
	
	public static int getRandomInt(int bound) {
		return rand.nextInt(bound);
	}
}
