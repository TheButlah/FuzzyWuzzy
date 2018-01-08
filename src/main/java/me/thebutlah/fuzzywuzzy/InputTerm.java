package me.thebutlah.fuzzywuzzy;

/**
 * Represents a linguistic term (which is a fuzzy set) used to describe any of the InputVariables.
 *
 * Example: IF weather == cold THEN heat = high. "cold" is the InputTerm.
 *
 * @author Ryan Butler
 */
public class InputTerm {

    private final String name;
    private final MembershipFunction func;

    /**
     * Constructs an InputTerm object with a trapezoidal membership function.
     *
     * @param start      The leftmost point of the trapezoid. Values left of this are zero.
     * @param upperLeft  The left "corner" of the trapezoid. Values are 1 from this point to `upperRight`.
     * @param upperRight The right "corner" of the trapezoid. Values are 1 from this point to `upperLeft`.
     * @param end        The rightmost point of the trapezoid. Values right of this are zero.
     */
    public InputTerm(String name, double start, double upperLeft, double upperRight, double end) {
        this.name = name;
        this.func = new MembershipFunction(start, upperLeft, upperRight, end);
    }

    /**
     * Constructs an InputTerm object with a given membership function.
     */
    public InputTerm(String name, MembershipFunction func) {
        this.name = name;
        this.func = func;
    }

    /**
     * Computes the extent of membership of an element of the fuzzy set that the InputTerm represents.
     *
     * @param input The value of the InputTerm to check membership in the fuzzy set for.
     * @return A number in [0,1] that represents the membership of the fuzzy set.
     */
    double getMembership(double input) {
        return func.evaluate(input);
    }

    /**
     * Gets the name of the InputTerm.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "InputTerm{" + name + "," + func + "}";
    }
}
