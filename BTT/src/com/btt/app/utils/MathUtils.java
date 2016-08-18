package com.btt.app.utils;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by zhouyun on 2016/8/15.
 */
public final class MathUtils {
    public static final int LEN = 8;

    private MathUtils() {

    }

    public static double add(double d1, double d2) {
        return new BigDecimal(d1).add(new BigDecimal(d2), MathContext.DECIMAL128).setScale(LEN, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double divide(double d1, double d2) {
        return new BigDecimal(d1).divide(new BigDecimal(d2), MathContext.DECIMAL128).setScale(LEN, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double multiply(double d1, double d2) {
        return new BigDecimal(d1).multiply(new BigDecimal(d2), MathContext.DECIMAL128).setScale(LEN, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
