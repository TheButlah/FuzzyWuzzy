package me.thebutlah.fuzzywuzzy;


import java.util.function.DoubleSupplier;

/**
 * This class describes antecedents with the form "variable == term" or "variable != term"
 */
public class FuzzyComparison extends Antecedent {
    private final DoubleSupplier func;
    private final String str;

    /**
     * Constructs a FuzzyComparision object from a function performing the comparison and an accompanying string
     * describing the comparison.
     * @param str The description of the comparison.
     * @param func The function performing the fuzzy comparison.
     */
    public FuzzyComparison(String str, DoubleSupplier func) {
        this.str = str;
        this.func = func;
    }

    @Override
    public double evaluate() {
        return func.getAsDouble();
    }

    @Override
    public String toString() {
        return str;
    }
}