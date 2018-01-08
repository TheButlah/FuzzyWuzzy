package me.thebutlah.fuzzywuzzy;

/**
 * This class describes Antecedents with the form "a AND b".
 *
 * @author Ryan Butler
 */
class FuzzyAND extends Antecedent {

    private final Antecedent left;
    private final Antecedent right; //should be null for NOT operations
    //private Antecedent[] operands;

    /**
     * Constructs the Antecedent that represents the fuzzy AND operation.
     * @param left The left operand.
     * @param right The right operand.
     */
    FuzzyAND(Antecedent left, Antecedent right) {
        this.left = left;
        this.right = right;
    }

    @Override
    double evaluate() {
        return Math.min(left.evaluate(), right.evaluate());
    }

    @Override
    public String toString() {
        return "(" + left + ") && (" + right + ")";
    }
}