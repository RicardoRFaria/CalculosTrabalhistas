package com.ricardofaria.salarioliquido.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrecisionUtil {

	private static final int MONETATY_SCALE = 2;

	public static BigDecimal createMonetaryBigDecimal(String value) {
		BigDecimal bigDecimal = new BigDecimal(value);
		return bigDecimal.setScale(MONETATY_SCALE, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal createMonetaryBigDecimal(float value) {
		return createMonetaryBigDecimal(Float.toString(value));
	}

	public static BigDecimal changeToMonetaryBidecimal(BigDecimal bigDecimal) {
		return bigDecimal.setScale(MONETATY_SCALE, RoundingMode.HALF_EVEN);
	}

}
