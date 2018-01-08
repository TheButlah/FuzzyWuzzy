package me.thebutlah.fuzzywuzzy;

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

    double getWeight() {
        return w;
    }

    Antecedent getAntecedent() {
        return a;
    }

    Consequent getConsequent() {
        return c;
    }

    @Override
    public String toString() {
        return "if " + a + " then " + c + " (weight: " + w + ")";
    }
}
