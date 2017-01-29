package me.thebutlah.fuzzywuzzy;

/**
 * Created by Ryan on 1/26/2017.
 */
public class InputVariable {

  public Antecedant is(LinguisticTerm term) {
    return new FuzzyOp.Is(this, term);
  }

  public Antecedant isNot(LinguisticTerm term) {
    return (new FuzzyOp.IsNot(this, term));
  }
}


