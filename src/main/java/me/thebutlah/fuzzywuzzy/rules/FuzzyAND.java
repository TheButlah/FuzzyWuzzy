package me.thebutlah.fuzzywuzzy.rules;

class FuzzyAND extends Antecedent {

  private Antecedent left;
  private Antecedent right; //should be null for NOT operations

  FuzzyAND(Antecedent left, Antecedent right) {
    this.left = left;
    this.right = right;
  }

  public double evaluate() {
    return Math.min(left.evaluate(),right.evaluate());
  }
}