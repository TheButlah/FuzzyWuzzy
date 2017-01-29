package me.thebutlah.fuzzywuzzy.rules;

class FuzzyOR extends Antecedent {

  private Antecedent left;
  private Antecedent right; //should be null for NOT operations

  FuzzyOR(Antecedent left, Antecedent right) {
    this.left = left;
    this.right = right;
  }

  public double evaluate() {
    return Math.max(left.evaluate(),right.evaluate());
  }
}