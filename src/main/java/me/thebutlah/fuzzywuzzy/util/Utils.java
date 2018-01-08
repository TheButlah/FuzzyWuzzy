package me.thebutlah.fuzzywuzzy.util;

/**
 * A Utility class filled with helper functions.
 *
 * @author Ryan Butler
 */
public final class Utils {

    /**
     * Returns whether `a` and `b` are near each other.
     * @param a The first number to compare.
     * @param b The second number to compare.
     * @param delta A small number that is the distance within which numbers are considered "near" each other.
     */
    public static boolean isNear(double a, double b, double delta) {
        double diff = Math.abs(a-b);
        return diff <= delta;
    }

    /**
     * Returns whether `a` and `b` are near each other.
     * @param a The first number to compare.
     * @param b The second number to compare.
     */
    public static boolean isNear(double a, double b) {
        return isNear(a, b, 0.0001);
    }

    //Prevent instantiating this class
    private Utils() {}
}
