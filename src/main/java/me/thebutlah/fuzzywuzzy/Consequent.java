package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.util.Pair;

import static me.thebutlah.fuzzywuzzy.util.Utils.isNear;

/**
 * Represents the consequent of the if statement for a FuzzyRule. This data structure can easily be made with the
 * `Antecedent.then()` syntax helper function.
 *
 * Example: IF weather == cold THEN heat = high. "heat = high" is the Consequent.
 *
 * @author Ryan Butler
 */
public class Consequent {

    private final OutputVariable[] vars;
    private final OutputTerm[] terms;

    /**
     * Constructs a Consequent from an array of OutputVariables with corresponding OutputTerms. This describes
     * the behavior of the Consequent should its Antecedent be determined to be true.
     *
     * NOTE: `vars` and `terms` should be the same length with matching indices indicating correspondence between the
     * OutputVariable and the OutputTerm. There should be no duplicates in the variables and the lists need to be
     * non-null and non-empty.
     *
     * @param vars An array of OutputVariables that corresponds to `terms`.
     * @param terms An array of OutputTerms that corresponds to `vars`.
     */
    Consequent(OutputVariable[] vars, OutputTerm[] terms) {
        assert vars.length == terms.length;
        this.vars = vars;
        this.terms = terms;
    }

    /**
     * Constructs a Consequent from an array of pairs of OutputVariables with corresponding OutputTerms. This describes
     * the behavior of the Consequent should its Antecedent be determined to be true.
     *
     * NOTE: There should be no duplicates in the variables and the array needs to be non-null and non-empty.
     *
     * @param pairs An array of pairs of OutputVariables and OutputTerms.
     */
    @SafeVarargs
    Consequent(Pair<OutputVariable, OutputTerm>... pairs) {
        vars = new OutputVariable[pairs.length];
        terms = new OutputTerm[pairs.length];
        for (int i=0; i<pairs.length; i++) {
            Pair<OutputVariable, OutputTerm> pair = pairs[i];
            vars[i] = pair.getFirst();
            terms[i] = pair.getSecond();
        }
    }

    /**
     * Evaluates the values the OutputVariables in the Consequent should take based on the provided strength value.
     * @param strength The normalized strength of the FuzzyRule (strengths for each FuzzyRule should sum to 1).
     */
    void evaluate(double strength) {
        for (int i=0; i<vars.length; i++) {
            OutputVariable var = vars[i];
            OutputTerm term = terms[i];
            double value = term.getValue() * strength;
            if (isNear(value, 0)) continue; // Dont touch the var if the rule didn't activate.
            double currentValue = var.getValueUnsafe();
            if (Double.isNaN(currentValue)) currentValue = 0;
            var.setValue(currentValue + value);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<vars.length-1; i++) {
            builder.append(vars[i].getName()).append(" = ").append(terms[i].getName()).append(", ");
        }
        //For efficiency, don't have a check inside the loop and instead loop on all but last and then run code on last.
        builder.append(vars[vars.length-1].getName()).append(" = ").append(terms[vars.length-1].getName());
        return builder.toString();
    }
}
