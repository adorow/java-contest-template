package com.github.adorow.template;

/**
 * Util class with helper functions for dealing with arrays. Normally used as base functions to be tweaked in new code.
 */
public class ArrayUtil {

    private ArrayUtil() {}

    /**
     * Swaps two elements in an array. No boundary checks are done, the user of this method has to do it.
     *
     * @param xs the array of elements.
     * @param i one of the index to be swaped.
     * @param j the other index to be swaped.
     */
    public static void swap(int[] xs, int i, int j) {
        int tmp = xs[i];
        xs[i] = xs[j];
        xs[j] = tmp;
    }

    // SEARCH FUNCTIONS

    /**
     * Executes a binary search, looking for a given element. <br/>
     * Expects the range of the search to be already ordered.
     *
     * @param soughtElement the element looked for.
     * @param list the array of elements.
     * @param ini the first index of the searched range.
     * @param end the last index of the searched range.
     * @return {@code -1} if the element is not found in the list, or the index of the element in the list, if it is found.
     */
    public static int binSearchExact(int soughtElement, int[] list, int ini, int end) {
        int mid = (end + ini) >>> 1;

        while (ini <= end) {
            if (list[mid] == soughtElement)
                return mid;

            if (list[mid] > soughtElement) {
                end = (mid - 1);
            } else {// list[mid] < soughtElement
                ini = (mid + 1);
            }
            mid = (end + ini) >>> 1;
        }

        return -1;
    }

    /**
     * Executes a binary search, looking for a given element. If the element is not found, the index of the next larger value is returned.<br/>
     * Expects the range of the search to be already ordered.
     *
     *
     * @param soughtElement the element looked for.
     * @param list the array of elements.
     * @param ini the first index of the searched range.
     * @param end the last index of the searched range.
     * @return the index of the element found, or the index of the closest larger number.
     */
    public static int binSearchUpperEqual(int soughtElement, int[] list, int ini, int end) {
        int mid = (end + ini) >> 1;

        while ((list[mid] != soughtElement) && ini < mid && mid < end) {
            if (list[mid] > soughtElement) {
                end = mid;
            } else {// list[mid] < soughtElement
                ini = mid + 1;
            }
            mid = (end + ini) >> 1;
        }
        if (list[mid] < soughtElement)
            mid++;

        return mid;
    }

    /**
     * Executes a binary search, looking for a given element. If the element is not found, the index of the next smaller value is returned.<br/>
     * Expects the range of the search to be already ordered.
     *
     * @param soughtElement the element looked for.
     * @param list the array of elements.
     * @param ini the first index of the searched range.
     * @param end the last index of the searched range.
     * @return the index of the element found, or the index of the closest smaller number.
     */
    public static int binSearchLowerEqual(int soughtElement, int[] list, int ini, int end) {
        int mid = (end + ini) >> 1;

        while ((list[mid] != soughtElement) && ini < mid && mid < end) {
            if (list[mid] > soughtElement) {
                end = mid - 1;
            } else {// list[mid] < soughtElement
                ini = mid;
            }
            mid = (end + ini) >> 1;
        }
        // FIXME: UNTESTED
        if (list[mid] > soughtElement)
            mid--;

        return mid;
    }

}
