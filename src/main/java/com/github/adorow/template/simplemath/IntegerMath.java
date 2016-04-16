package com.github.adorow.template.simplemath;

/**
 * Utilitary methods for mathematical operations on integers.
 */
public final class IntegerMath {

    private IntegerMath(){}

    /**
     * Integer power of 2.
     */
    public static int pow2(int v) {
        return v * v;
    }

    /**
     * Integer power of 2 on {@code long}s.
     */
    public static long pow2(long v) {
        return v * v;
    }

    /**
     * Multiplication by 10.
     */
    public static int times10(int v) {
        return (v << 3) + (v << 1);
    }



}
