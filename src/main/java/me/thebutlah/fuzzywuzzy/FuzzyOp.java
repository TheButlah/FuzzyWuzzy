package me.thebutlah.fuzzywuzzy;

/**
 * Created by Ryan on 1/27/2017.
 */
public class FuzzyOp {

  public static double and(double... operands) {
    double result = Double.MAX_VALUE;
    for (double d : operands) {
      result = Math.min(result, d);
    }
    return result;
  }

  public static double or(double... operands) {
    double result = Double.MIN_VALUE;
    for (double d : operands) {
      result = Math.max(result, d);
    }
    return result;
  }

  public static double not(double operand) {
    return 1-operand;
  }


  public class Is extends FuzzyTerm {
    private InputVariable var;
    private LinguisticTerm[] terms;

    public Is(InputVariable var, LinguisticTerm... terms) {
      this.var = var;
      this.terms = terms;
    }
  }

  public class IsNot extends FuzzyTerm {
    private InputVariable var;
    private LinguisticTerm[] terms;

    public IsNot(InputVariable var, LinguisticTerm... terms) {
      this.var = var;
      this.terms = terms;
    }
  }
}
