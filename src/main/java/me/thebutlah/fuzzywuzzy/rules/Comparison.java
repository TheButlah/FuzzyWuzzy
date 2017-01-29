package me.thebutlah.fuzzywuzzy.rules;


import java.util.function.DoubleSupplier;

public class Comparison extends Antecedent {
  private DoubleSupplier func;

  public Comparison(DoubleSupplier func) {
    this.func = func;
  }

  public double evaluate() {
    return func.getAsDouble();
  }
}