package me.thebutlah.fuzzywuzzy.rules;

class FuzzyNOT extends Antecedent {

  private Antecedent operand;

  FuzzyNOT(Antecedent operand) {
    this.operand = operand;
  }

  public double evaluate() {
    return 1-operand.evaluate();
  }
}