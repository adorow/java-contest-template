package com.github.adorow.template.geometry;

/**
 * Utilitary for 3D geometry.
 */
public class Geometry3dUtil {

    /**
     * Calculates the distance between two 3D points.
     *
     * @param x1 x of point 1
     * @param y1 y of point 1
     * @param z1 z of point 1
     * @param x2 x of point 2
     * @param y2 y of point 2
     * @param z2 z of point 2
     * @return the difference between the two points.
     */
    public static double distance(long x1, long y1, long z1, long x2, long y2, long z2) {
        return Math.sqrt(pow2(x1-x2) + pow2(y1-y2) + pow2(z1-z2));
    }

    private static long pow2(long v) {
        return v * v;
    }

}
