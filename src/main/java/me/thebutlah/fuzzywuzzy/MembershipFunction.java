package me.thebutlah.fuzzywuzzy;

/**
 * This class represents a membership function for a fuzzy set. Membership functions range from [0,1] and represent the
 * degree to which an input belongs to the fuzzy set. Hence, each linguistic term (specifically the `InputTerm` class)
 * has an accompanying membership function.
 */
public class MembershipFunction {

    private final double start, upperLeft, upperRight, end;

    private final double risingSlope, fallingSlope;

    /**
     * Constructs a trapezoidal membership function for a fuzzy set. Membership function ranges from 0 to 1.
     *
     * @param start      The leftmost point of the trapezoid. Values left of this are zero.
     * @param upperLeft  The left "corner" of the trapezoid. Values are 1 from this point to `upperRight`.
     * @param upperRight The right "corner" of the trapezoid. Values are 1 from this point to `upperLeft`.
     * @param end        The rightmost point of the trapezoid. Values right of this are zero.
     */
    public MembershipFunction(double start, double upperLeft, double upperRight, double end) {
        this.start = start;
        this.upperLeft = upperLeft;
        this.upperRight = upperRight;
        this.end = end;

        this.risingSlope = 1.0 / (upperLeft - start);
        this.fallingSlope = 1.0 / (end - upperRight); //NOTE: This is a positive value

    }

    /**
     * Evaluates the MembershipFunction for an input. Returns a number from 0 to 1, where 1 is full membership and 0 is
     * no membership.
     *
     * @param input The value to determine membership of.
     * @return A number in [0,1] that describes the membership of `input`.
     */
    public double evaluate(double input) {
        if (input <= start) {
            //out of bounds
            return 0;
        } else if (input < upperLeft) {
            //rising slope
            return (input - start) * risingSlope;
        } else if (input <= upperRight) {
            //plateau
            return 1.0;
        } else if (input < end) {
            //falling slope
            return (end - input) * fallingSlope;
        } else {
            //out of bounds
            return 0;
        }
    }
}
