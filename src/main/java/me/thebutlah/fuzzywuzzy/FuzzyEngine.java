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

    private ArrayList<InputVariable> iVars = new ArrayList<>();
    private ArrayList<OutputVariable> oVars = new ArrayList<>();

    private FuzzyRule[] rules;

    private boolean isValid = false;

    public FuzzyEngine() {

    }

    public InputVariable addInputVariable(String name) {
        InputVariable iVar = new InputVariable(name, this);
        iVars.add(iVar);
        return iVar;
    }

    public OutputVariable addOutputVariable(String name) {
        OutputVariable oVar = new OutputVariable(name, this);
        oVars.add(oVar);
        return oVar;
    }

    public void addFuzzyRules(FuzzyRule... rules) {
        this.rules = rules;
    }



    /**
     * Runs the FuzzyEngine. It operates on whatever crisp inputs are currently set for the InputVariables to produce a
     * crisp output for each of the OutputVariables.
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
        if (isNear(strengthSum, 0)) return; //If none of the rules activated, then the OutputVariables should be NaN.
        // Apply strengths to the Consequents
        for (int i=0; i<rules.length; i++) {
            FuzzyRule r = rules[i];
            Consequent c = r.getConsequent();
            c.evaluate(strengths[i]/strengthSum);
        }
        this.isValid = true;
    }

    void invalidate() {
        if (!this.isValid) return;
        this.isValid = false;
        for (OutputVariable var : oVars) {
            var.setValue(Double.NaN);
        }
    }

    boolean isValid() {
        return this.isValid;
    }

    /**
     * Describes the rules of this instance of a FuzzyEngine as a string.
     * @return The string describing the rules.
     */
    public String describeRules() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<rules.length-1; i++) {
            result.append(rules[i]).append("\n");
        }
        result.append(rules[rules.length-1]);
        return result.toString();
    }

}
