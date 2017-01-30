package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.rules.FuzzyRule;

/**
 * Created by Ryan on 1/26/2017.
 */
public class FuzzyEngine {

  protected FuzzyRule[] rules;

  public void addRules(FuzzyRule... rules) {
    this.rules = rules;
  }

  public double evaluate() {
    double result = 0;
    double weightSum = 0;
    for (FuzzyRule r : rules) {
      double weight = r.getWeight();
      double targetValue = r.getTargetValue();
      weightSum += weight;
      result += weight*targetValue;
    }
    return result/weightSum;
  }

}
