package me.thebutlah.fuzzywuzzy;

/**
 * This class describes a single rule in the FuzzyEngine. It is composed of an Antecedent and a consequent. Each rule
 * also has an associated weight. Rules with larger weights will have more importance.
 *
 * Example: IF weather == cold THEN heat = high. "weather == cold" is the Antecedent, "heat = high" is the consequent.
 * Together they make up the rule.
 *
 * @author Ryan Butler
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
     * @param antecedent The antecedent of the rule (first half of if statement).
     * @param consequent The consequent of the rule (second half of if statement).
     * @param weight The weight of the FuzzyRule.
     */
    FuzzyRule(Antecedent antecedent, Consequent consequent, double weight){
        this.a = antecedent;
        this.c = consequent;
        this.w = weight;
    }

    /**
     * Gets the weight of the FuzzyRule.
     */
    double getWeight() {
        return w;
    }

    /**
     * Gets the Antecedent of the FuzzyRule.
     */
    Antecedent getAntecedent() {
        return a;
    }

    /**
     * Gets the Consequent of the FuzzyRule.
     */
    Consequent getConsequent() {
        return c;
    }

    @Override
    public String toString() {
        return "if " + a + " then " + c + " (weight: " + w + ")";
    }
}
