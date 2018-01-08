package me.thebutlah.fuzzywuzzy;

import java.util.ArrayList;
import java.util.Arrays;

import static me.thebutlah.fuzzywuzzy.util.Utils.isNear;

/**
 * FuzzyEngine performs all the logic and mathematics necessary for a Sugeno-Type Fuzzy Inference System (FIS). A FIS is
 * a decision making system that can use vague concepts that humans use (like small, large, hot, cold) in traditional
 * logical operations.
 *
 * "Crisp" inputs are regular numbers. They get "fuzzified", then passed through the FIS using the rules supplied, and
 * then get "defuzzified" into crisp outputs. Those outputs can then be used as normal.
 *
 * @author Ryan Butler
 */
public class FuzzyEngine {

    private final String name;
    private final ArrayList<OutputVariable> oVars = new ArrayList<>(); //Used to enable reset of OutputVariables
    private FuzzyRule[] rules;

    private boolean isValid = false;

    public FuzzyEngine(String name) {
        this.name = name;
    }

    /**
     * Adds an InputVariable to the FuzzyEngine.
     * NOTE: You must call `FuzzyEngine.defuzzify()` to validate the engine before running `OutputVariable.getValue()`.
     *
     * @param name The name of the InputVariable.
     * @return The InputVariable that was added.
     */
    public InputVariable addInputVariable(String name) {
        this.isValid = false;
        return new InputVariable(name, this);
    }

    /**
     * Adds an OutputVariable to the FuzzyEngine.
     * NOTE: You must call `FuzzyEngine.defuzzify()` to validate the engine before running `OutputVariable.getValue()`.
     *
     * @param name The name of the OutputVariable.
     * @return The OutputVariable that was added.
     */
    public OutputVariable addOutputVariable(String name) {
        this.isValid = false;
        OutputVariable oVar = new OutputVariable(name, this);
        oVars.add(oVar);
        return oVar;
    }

    /**
     * Sets one or more FuzzyRules to define the logic for the engine. FuzzyRules can be created through
     * `Antecedent.then()` and the helper functions in FuzzyLogic.
     * NOTE: You must call `FuzzyEngine.defuzzify()` to validate the engine before running `OutputVariable.getValue()`.
     *
     * @param rules An Array of rules. Should not be null or empty.
     */
    public void setFuzzyRules(FuzzyRule... rules) {
        this.isValid = false;
        this.rules = rules;
    }



    /**
     * Runs the FuzzyEngine. It operates on whatever crisp inputs are currently set for the InputVariables to produce a
     * crisp output for each of the OutputVariables. Also validates the engine.
     */
    public void defuzzify() {
        assert !isValid; // Why would you defuzzify when you don't need to? It wouldn't hurt but why do it?
        double strengthSum = 0;
        double[] strengths = new double[rules.length];
        // Compute Antecedent strengths and strengthSum
        for (int i=0; i<rules.length; i++) {
            FuzzyRule r = rules[i];
            Antecedent a = r.getAntecedent();
            double strength = a.evaluate() * r.getWeight();
            strengths[i] = strength;
            strengthSum += strength;
        }
        if (isNear(strengthSum, 0)) {
            this.isValid = true;
            return; //If none of the rules activated, then the OutputVariables should be NaN.
        }
        // Apply strengths to the Consequents
        for (int i=0; i<rules.length; i++) {
            FuzzyRule r = rules[i];
            Consequent c = r.getConsequent();
            c.evaluate(strengths[i]/strengthSum);
        }
        this.isValid = true;
    }

    /**
     * Invalidates the state of the engine. Should be called whenever changing the value of an InputVariable.
     * NOTE: You must call `FuzzyEngine.defuzzify()` to validate the engine before running `OutputVariable.getValue()`.
     */
    void invalidate() {
        if (!this.isValid) return;
        this.isValid = false;
        for (OutputVariable var : oVars) {
            var.setValue(Double.NaN);
        }
    }

    /**
     * Returns whether the FuzzyEngine state is valid. A valid state means that the OutputVariables have valid values.
     * NOTE: You must call `FuzzyEngine.defuzzify()` to validate the engine before running `OutputVariable.getValue()`.
     */
    public boolean isValid() {
        return this.isValid;
    }

    /**
     * Describes the rules of this instance of a FuzzyEngine as a string.
     *
     * @return The string describing the rules.
     */
    public String describeRules() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<rules.length-1; i++) {
            result.append(rules[i]).append("; ");
        }
        result.append(rules[rules.length-1]);
        return result.toString();
    }

    /**
     * Gets the name of the FuzzyEngine.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FuzzyEngine{" + name + "}";
    }

}
