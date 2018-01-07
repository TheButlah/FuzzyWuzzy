package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.rules.Consequent;

/**
 * Created by Ryan on 1/26/2017.
 */
public class OutputVariable {

    private final String name;
    private double value = Double.NaN;

    public OutputVariable(String name) {
        this.name = name;
    }

    public double getValue() {
        return this.value;
    }

    public Consequent set(OutputTerm term) {
        return new Consequent(term);
    }

    public String getName() {
        return name;
    }
}
