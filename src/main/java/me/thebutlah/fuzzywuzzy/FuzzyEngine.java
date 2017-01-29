package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.rules.FuzzyRule;

/**
 * Created by Ryan on 1/26/2017.
 */
public class FuzzyEngine {

  protected InputVariable[] inputs;
  protected OutputVariable[] outputs;
  protected FuzzyRule[] rules;

  public void addInputVariables(InputVariable... inputs) {
    this.inputs = inputs;
  }

  public void addOutputVariables(OutputVariable... outputs) {
    this.outputs = outputs;
  }

  public void addRules(FuzzyRule... rules) {
    this.rules = rules;
  }

  public void evaluate() {
    for (FuzzyRule r : rules) {
      //r.getWeight();
    }
  }

}
