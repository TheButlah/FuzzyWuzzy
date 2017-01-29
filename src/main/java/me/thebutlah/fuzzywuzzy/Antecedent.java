package me.thebutlah.fuzzywuzzy;

import com.oracle.jrockit.jfr.InstantEvent;

import java.util.function.DoubleSupplier;
import java.util.function.Function;

/**
 * Created by Ryan on 1/27/2017.
 */
public abstract class Antecedent {

  public abstract double evaluate();

  enum OperationType {
    OR, AND, NOT
  }

  class Operation extends Antecedent{

    private OperationType type;
    private Antecedent left;
    private Antecedent right; //should be null for NOT operations

    public Operation(OperationType type, Antecedent left, Antecedent right) {
      this.type = type;
      this.left = left;
      this.right = right;
    }

    public double evaluate() {
      switch(type) {
        case AND:
          return Math.min(left.evaluate(),right.evaluate());
        case OR:
          return Math.max(left.evaluate(), right.evaluate());
        case NOT: default:
          return 1-left.evaluate();
      }
    }
  }
}
