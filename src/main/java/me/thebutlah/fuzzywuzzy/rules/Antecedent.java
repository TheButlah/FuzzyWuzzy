package me.thebutlah.fuzzywuzzy.rules;

/**
 * Created by Ryan on 1/27/2017.
 */
public abstract class Antecedent {

  public FuzzyRule then(Consequent c) {
    return new FuzzyRule(this,c);
  }
  public abstract double evaluate();

}
