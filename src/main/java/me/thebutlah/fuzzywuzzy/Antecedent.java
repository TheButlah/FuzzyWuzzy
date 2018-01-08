package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.util.Pair;

/**
 * Represents the guard of the if statement in a FuzzyRule
 *
 */
public abstract class Antecedent {

    /**
     * Links this antecedent into the supplied consequent(s), forming a FuzzyRule
     */
    @SafeVarargs
    public final FuzzyRule then(Pair<OutputVariable, OutputTerm>... pairs) {
        return new FuzzyRule(this, new Consequent(pairs));
    }

    /**
     * Evaluates the antecedent for the rule.
     * This is a fuzzy value that will be combined with others to eventually describe the strength of the rule.
     */
    abstract double evaluate();

    @Override
    public abstract String toString();

}
