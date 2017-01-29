package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.rules.FuzzyRule;

/**
 * Created by Ryan on 1/26/2017.
 */
public class FuzzyEngine {

  protected InputVariable[] inputs;
  protected OutputVariable output;
  protected FuzzyRule[] rules;

  public void addInputVariables(InputVariable... inputs) {
    this.inputs = inputs;
  }

  public void addOutputVariable(OutputVariable output) {
    this.output = output;
  }

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
