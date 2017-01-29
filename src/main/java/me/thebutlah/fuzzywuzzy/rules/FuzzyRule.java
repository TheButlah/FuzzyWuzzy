package me.thebutlah.fuzzywuzzy.rules;

import me.thebutlah.fuzzywuzzy.InputVariable;
import me.thebutlah.fuzzywuzzy.OutputVariable;

/**
 * Created by Ryan on 1/26/2017.
 */
public class FuzzyRule {

  private InputVariable[] inputs;
  private OutputVariable[] outputs;
  private Antecedent a;
  private Consequent c;

  FuzzyRule(Antecedent antecedent, Consequent consequent) {
    this.a = antecedent;
    this.c = consequent;
  }

  public void evaluate() {
    double weight = a.evaluate();

  }
}
