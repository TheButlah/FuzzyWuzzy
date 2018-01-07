package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.rules.FuzzyRule;

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

    private FuzzyRule[] rules;

    /**
     * Constructs the FuzzyEngine.
     *
     * @param rules
     */
    public FuzzyEngine(FuzzyRule... rules) {
        this.rules = rules;
    }

    /**
     * Runs the FuzzyEngine. It operates on whatever crisp inputs are currently set for the InputVariables to produce a
     * crisp output.
     *
     * @return A number that represents the crisp output of the FuzzyEngine.
     */
    public double defuzzify() {
        double result = 0;
        double strengthSum = 0;
        for (FuzzyRule r : rules) {
            double strength = r.computeRuleStrength();
            double consequentVal = r.getConsequentValue();
            strengthSum += strength;
            result += strength * consequentVal;
        }
        return result / strengthSum;
    }

    /**
     * Describes the rules of this instance of a FuzzyEngine as a string.
     * @return The string describing the rules.
     */
    public String describeRules() {
        StringBuilder result = new StringBuilder();
        for (FuzzyRule r : rules) {
            result.append(r).append("\n");
        }
        return result.toString();
    }

}
