package me.thebutlah.fuzzywuzzy;

/**
 * This class describes Antecedents with the form "a OR b".
 *
 * @author Ryan Butler
 */
class FuzzyOR extends Antecedent {

    private final Antecedent left;
    private final Antecedent right;

    /**
     * Constructs the Antecedent that represents the fuzzy OR operation.
     * @param left The left operand.
     * @param right The right operand.
     */
    FuzzyOR(Antecedent left, Antecedent right) {
        this.left = left;
        this.right = right;
    }

    @Override
    double evaluate() {
        return Math.max(left.evaluate(), right.evaluate());
    }

    @Override
    public String toString() {
        return "(" + left + ") || (" + right + ")";
    }
}