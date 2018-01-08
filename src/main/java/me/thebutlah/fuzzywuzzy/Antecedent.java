package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.util.Pair;

/**
 * Represents the antecedent of the if statement in a FuzzyRule. This data structure is recursive and you can compose more
 * complex Antecedents out of simpler Antecedents using the syntax helper functions in the FuzzyLogic class.
 *
 * Example: IF weather == cold THEN heat = high. "weather == cold" is the Antecedent.
 *
 * @author Ryan Butler
 */
public abstract class Antecedent {

    /**
     * Syntax helper function to create a FuzzyRule. This constructs a Consequent from `pairs` and then joins this
     * Antecedent and the newly constructed Consequent together into a FuzzyRule.
     */
    @SafeVarargs
    public final FuzzyRule then(Pair<OutputVariable, OutputTerm>... pairs) {
        return new FuzzyRule(this, new Consequent(pairs));
    }

    /**
     * Evaluates the antecedent for the rule.
     * This will be combined with other antecedents to eventually describe the strength of this rule.
     * @return The strength of the Antecedent.
     */
    abstract double evaluate();

    @Override
    public abstract String toString();

}
