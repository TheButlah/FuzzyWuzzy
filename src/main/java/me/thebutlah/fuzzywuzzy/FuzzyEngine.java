package me.thebutlah.fuzzywuzzy;

/**
 * Created by Ryan on 1/26/2017.
 */
public abstract class FuzzyEngine {

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

  public abstract void evaluate();

}
