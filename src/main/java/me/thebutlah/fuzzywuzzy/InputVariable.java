package me.thebutlah.fuzzywuzzy;

/**
 * Represents a variable for the input of the FuzzyEngine.
 *
 * @author Ryan Butler
 */
public class InputVariable {

    private final String name;
    private final FuzzyEngine engine;
    private double value = Double.NaN;

    /**
     * Constructs an InputVariable object inside of a FuzzyEngine.
     *
     * @param name The name of the InputVariable.
     * @param engine The engine that the InputVariable belongs to.
     */
    InputVariable(String name, FuzzyEngine engine) {
        this.name = name;
        this.engine = engine;
    }

    /**
     * Syntax helper function to generate an Antecedent by comparing the InputVariable to an InputTerm.
     *
     * @param term The term to compare to.
     * @return An Antecedent object that represents the fuzzy comparison.
     */
    public Antecedent is(InputTerm term) {
        String str = name + " == " + term.getName();
        return new FuzzyComparison(str, () -> term.getMembership(value));
    }

    /**
     * Syntax helper function to generate an Antecedent by comparing the InputVariable to an InputTerm.
     *
     * @param term The term to compare to.
     * @return An Antecedent object that represents the fuzzy comparison.
     */
    public Antecedent isNot(InputTerm term) {
        String str = name + " != " + term.getName();
        return new FuzzyComparison(str, () -> 1 - term.getMembership(value));
    }

    /**
     * Gets the name of the InputVariable
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the InputVariable. This is how you feed data into the FuzzyEngine.
     * NOTE: You must call `FuzzyEngine.defuzzify()` to validate the engine before running `OutputVariable.getValue()`.
     *
     * @param value The value to input.
     */
    public void setValue(double value) {
        this.value = value;
        engine.invalidate();
    }

    /**
     * Gets the value stored in the InputVariable. This is what the FuzzyEngine will use when computing outputs.
     * @return The value stored.
     */
    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "InputVariable{" + name + ": " + value + "}";
    }
}


