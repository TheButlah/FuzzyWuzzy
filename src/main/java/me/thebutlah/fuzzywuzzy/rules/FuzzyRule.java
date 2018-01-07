package me.thebutlah.fuzzywuzzy.rules;

import me.thebutlah.fuzzywuzzy.InputVariable;
import me.thebutlah.fuzzywuzzy.OutputVariable;

/**
 * Created by Ryan on 1/26/2017.
 */
public class FuzzyRule {

    private final Antecedent a;
    private final Consequent c;
    private final double w;

    /**
     * Constructs a FuzzyRule object. `antecedent` and `consequent` are parts of an if statement:
     * "if ANTECEDENT then CONSEQUENT". The FuzzyRule is weighted by a weight of 1. Rules with larger weights will have
     * more impact on the final result.
     * @param antecedent The antecedent of the rule (first half of if statement)
     * @param consequent The consequent of the rule (second half of if statement)
     */
    FuzzyRule(Antecedent antecedent, Consequent consequent) {
        this(antecedent, consequent, 1.0);
    }

    /**
     * Constructs a FuzzyRule object. `antecedent` and `consequent` are parts of an if statement:
     * "if ANTECEDENT then CONSEQUENT". The FuzzyRule is weighted by `weight`. Rules with larger weights will have more
     * impact on the final result.
     * @param antecedent The antecedent of the rule (first half of if statement)
     * @param consequent The consequent of the rule (second half of if statement)
     * @param weight The weight of the FuzzyRule.
     */
    FuzzyRule(Antecedent antecedent, Consequent consequent, double weight){
        this.a = antecedent;
        this.c = consequent;
        this.w = weight;
    }

    /**
     * Evaluates the strength of the antecedent and returns that strength multiplied by the weight of the FuzzyRule.
     * NOTE: `weight` is not guaranteed to be normalized so that the weights of all the FuzzyRules sum to 1. The code
     * that uses the rule should be in charge of normalizing the value to ensure that the weights all sum to 1.
     */
    public double computeRuleStrength() {
        return a.evaluate() * this.w;
    }

    /**
     * Gets the weight of this FuzzyRule.
     * @return The weight.
     */
    public double getWeight() {
        return w;
    }

    /**
     * Gets the value of the consequent.
     * @return The value of the consequent.
     */
    public double getConsequentValue() {
        return c.getValue();
    }

    @Override
    public String toString() {
        return "if " + a + " then " + c + " (weight: " + w + ")";
    }
}
