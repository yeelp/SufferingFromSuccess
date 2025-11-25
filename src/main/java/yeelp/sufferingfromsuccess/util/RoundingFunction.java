package yeelp.sufferingfromsuccess.util;

import java.util.function.BiFunction;

public final class RoundingFunction implements BiFunction<Double, Integer, Integer>{

	BiFunction<Double, Integer, Double> f;
	
	@Override
	public Integer apply(Double t, Integer u) {
		return (int) Math.round(this.f.apply(t, u));
	}
	
	public RoundingFunction(BiFunction<Double, Integer, Double> f) {
		this.f = f;
	}
}
