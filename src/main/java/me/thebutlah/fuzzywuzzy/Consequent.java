package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.util.Pair;

import static me.thebutlah.fuzzywuzzy.util.Utils.isNear;

public class Consequent {

    private final OutputVariable[] vars;
    private final OutputTerm[] terms;

    Consequent(OutputVariable[] vars, OutputTerm[] terms) {
        assert vars.length == terms.length;
        this.vars = vars;
        this.terms = terms;
    }

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

    /**
     * Evaluates the OutputVariables in the Consequent based on the provided strength value.
     * @param strength The normalized strength of the FuzzyRule.
     */
    public void evaluate(double strength) {
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
}
