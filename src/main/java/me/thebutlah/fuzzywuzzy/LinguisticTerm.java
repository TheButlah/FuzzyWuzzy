package me.thebutlah.fuzzywuzzy;

/**
 * Created by Ryan on 1/26/2017.
 */
public class LinguisticTerm {

  private final String name;
  private final MembershipFunction func;

  public LinguisticTerm(String name, MembershipFunction func) {
    this.name = name;
    this.func = func;
  }

  public double getMembership(double input) {
    return func.evaluate(input);
  }
}
