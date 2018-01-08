package me.thebutlah.fuzzywuzzy;

public class InputVariable {

    private final String name;
    private final FuzzyEngine engine;
    private double value = Double.NaN;

    InputVariable(String name, FuzzyEngine engine) {
        this.name = name;
        this.engine = engine;
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
        engine.invalidate();
    }
}


