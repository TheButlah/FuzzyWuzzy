package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.util.Pair;

/**
 * Represents a variable for the output of the FuzzyEngine.
 *
 * @author Ryan Butler
 */
public class OutputVariable {

    private final String name;
    private final FuzzyEngine engine;
    private double value = Double.NaN;

    /**
     * Constructs an OutputVariable object inside of a FuzzyEngine.
     * @param name The name of the OutputVariable.
     * @param engine The engine that the OutputVariable belongs to.
     */
    OutputVariable(String name, FuzzyEngine engine) {
        this.name = name;
        this.engine = engine;
    }

    /**
     * Gets the value of the OutputVariable. Should only be called after `engine.defuzzify()`.
     *
     * NOTE: Sometimes this value may be NaN. This indicates that either one of the InputVariables was NaN or there was
     * insufficient logic in the FuzzyRules to cover this particular arrangement of InputVariables. Sometimes this is
     * useful - you can treat a NaN result as the "default" case.
     *
     * @return A double representing the value of the OutputVariable. Could be NaN.
     */
    public double getValue() {
        //This should really throw an exception but for speed we will place the burden of correctness on the user.
        assert engine.isValid();
        return this.value;
    }

    /**
     * Gets the value of the OutputVariable. This method is for internal use only and doesn't ensure the value is valid.
     * @return A double representing the value of the OutputVariable. Could be NaN.
     */
    double getValueUnsafe() {
        return this.value;
    }

    /**
     * Sets the value of the OutputVariable. Internal use only.
     */
    void setValue(double value) {
        this.value = value;
    }

    /**
     * Syntax helper function. Pass to `Antecedent.then()` to create a FuzzyRule.
     * @param term The OutputTerm that this OutputVariable should take as an output if the rule is true.
     * @return A Pair of OutputVariable and OutputTerm objects.
     */
    public Pair<OutputVariable, OutputTerm> set(OutputTerm term) {
        return new Pair<>(this, term);
    }

    /**
     * Gets the name of the OutputVariable.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "OutputVariable{" + name + ": " + value + "}";
    }
}
