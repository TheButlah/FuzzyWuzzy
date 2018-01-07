package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.rules.Antecedent;
import me.thebutlah.fuzzywuzzy.rules.FuzzyComparison;

public class InputVariable {

    private final String name;
    private double value = Double.NaN;

    public InputVariable(String name) {
        this.name = name;
    }

    public Antecedent is(InputTerm term) {
        String str = name + " == " + term.getName();
        return new FuzzyComparison(str, () -> term.getMembership(value));
    }

    public Antecedent isNot(InputTerm term) {
        String str = name + " != " + term.getName();
        return new FuzzyComparison(str, () -> 1 - term.getMembership(value));
    }

    public String getName() {
        return name;
    }

    public void setValue(double value) {
        this.value = value;
    }
}


