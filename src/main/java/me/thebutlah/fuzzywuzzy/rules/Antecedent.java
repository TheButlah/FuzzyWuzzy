package me.thebutlah.fuzzywuzzy.rules;

import com.oracle.jrockit.jfr.InstantEvent;

import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Ryan on 1/27/2017.
 */
public abstract class Antecedent {

  public abstract double evaluate();

}
