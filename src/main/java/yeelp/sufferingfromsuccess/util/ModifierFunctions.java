package yeelp.sufferingfromsuccess.util;

import java.util.function.BiFunction;

import net.minecraft.util.math.MathHelper;

public enum ModifierFunctions implements BiFunction<Double, Integer, Double> {
	LINEAR {
		@Override
		public Double apply(Double t, Integer u) {
			return t*u;
		}
	},
	LOGARITHMIC {
		@Override
		public Double apply(Double t, Integer u) {
			return t*Math.log(u + 1);
		}
	},
	LIMITING_VALUE {
		@Override
		public Double apply(Double t, Integer u) {
			if(t == 0) {
				return 0.0;
			}
			return u/(u + t);
		}
	},
	POLYNOMIAL {
		@Override
		public Double apply(Double t, Integer u) {
			return MathHelper.clamp(t*u*u, 0, 200);
		}
	};

}
