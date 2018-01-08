package me.thebutlah.fuzzywuzzy;

/**
 * Syntax helper class to perform fuzzy logic operations on Antecedents to combine them into a single Antecedent.
 *
 * @author Ryan Butler
 */
public final class FuzzyLogic {

    /**
     * Performs fuzzy AND operation on several Antecedents to combine them into a single Antecedent.
     *
     * @return An Antecedent object representing the result of the fuzzy operation.
     */
    public static Antecedent and(Antecedent... operands) {
        Antecedent result = operands[0];
        for (int i = 1; i < operands.length; i++) {
            result = new FuzzyAND(result, operands[i]);
        }
        return result;
    }

    /**
     * Performs fuzzy OR operation on several Antecedents to combine them into a single Antecedent.
     *
     * @return An Antecedent object representing the result of the fuzzy operation.
     */
    public static Antecedent or(Antecedent... operands) {
        Antecedent result = operands[0];
        for (int i = 1; i < operands.length; i++) {
            result = new FuzzyOR(result, operands[i]);
        }
        return result;
    }

    /**
     * Performs fuzzy NOT operation on an Antecedent.
     *
     * @return An Antecedent object representing the result of the fuzzy operation.
     */
    public static Antecedent not(Antecedent operand) {
        return new FuzzyNOT(operand);
    }

    //Prevent instantiating this class
    private FuzzyLogic() {
    }

}
