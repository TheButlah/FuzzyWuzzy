package me.thebutlah.fuzzywuzzy;

import me.thebutlah.fuzzywuzzy.rules.Consequent;

/**
 * Created by Ryan on 1/26/2017.
 */
public class OutputVariable<T extends Enum> {

  private final String name;
  private double value;

  public OutputVariable(String name) {
    this.name = name;
  }

  public Consequent set(OutputTerm term) {
    return new Consequent(term);
  }

  public double getValue() {
    return value;
  }

  void setValue(double value) {
    this.value = value;
  }
}
