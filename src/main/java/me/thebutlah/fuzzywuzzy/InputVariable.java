package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.rules.Antecedent;
import me.thebutlah.fuzzywuzzy.rules.FuzzyComparison;

import java.util.function.DoubleSupplier;

/**
 * Created by Ryan on 1/26/2017.
 */
public class InputVariable {

  private final String name;
  private Double value;

  public InputVariable(String name) {
    this.name = name;
  }

  public Antecedent is(LinguisticTerm term) {
    final double result = term.getMembership(value);
    return new FuzzyComparison(() -> {return result;});
  }

  public Antecedent isNot(LinguisticTerm term) {
    final double result = 1-term.getMembership(value);
    return new FuzzyComparison(() -> {return result;});
  }
}


