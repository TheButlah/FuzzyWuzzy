package me.thebutlah.fuzzywuzzy;

/**
 * This class describes Antecedents with the form "NOT a".
 *
 * @author Ryan Butler
 */
class FuzzyNOT extends Antecedent {

    private final Antecedent operand;

    /**
     * Constructs the Antecedent that represents the fuzzy NOT operation.
     * @param operand The operand to negate.
     */
    FuzzyNOT(Antecedent operand) {
        this.operand = operand;
    }

    @Override
    double evaluate() {
        return 1 - operand.evaluate();
    }

    @Override
    public String toString() {
        return "!" + operand.toString();
    }
}