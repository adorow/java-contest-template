package com.github.adorow.template.simplemath;

/**
 * Utilitary class for simple numeric functions.
 */
public class NumberUtil {

    private NumberUtil() {}

    /**
     * Finds the smallest of three numbers.
     * @return the smallest of all numbers.
     */
    public static int min(int i, int j, int k) {
        return (i < j && i < k) ? i : (j < k) ? j : k;
    }

    /**
     * Finds the larger of three numbers.
     * @return the largest of all numbers.
     */
    public static int max(int i, int j, int k) {
        return (i > j && i > k) ? i : (j > k) ? j : k;
    }

}
