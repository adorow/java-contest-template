package com.github.adorow.template.geometry;

/**
 * Utilitary for 2D geometry.
 */
public class Geometry2dUtil {

    private Geometry2dUtil() {}

    /**
     * Calculates the distance between two 2D points.
     *
     * @param x1 x of point 1
     * @param y1 y of point 1
     * @param x2 x of point 2
     * @param y2 y of point 2
     * @return the distance between the two points.
     */
    public static double distance(long x1, long y1, long x2, long y2) {
        return Math.sqrt(pow2(x1-x2) + pow2(y1-y2));
    }

    private static long pow2(long v) {
        return v * v;
    }

}
