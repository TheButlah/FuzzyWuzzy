import me.thebutlah.fuzzywuzzy.*;
import org.junit.Test;


import static me.thebutlah.fuzzywuzzy.rules.FuzzyLogic.*;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 1/29/2017.
 */
public class FuzzyEngineTest {

  @Test
  public void test() {

    //BEGIN INITIALIZING ENGINE
    InputVariable service = new InputVariable("service");
    InputVariable food = new InputVariable("food");

    InputTerm poor = new InputTerm("poor", 0,1,3,4,1);
    InputTerm average = new InputTerm("average", 3,4,6,7,1);
    InputTerm excellent = new InputTerm("excellent", 6,7, Double.MAX_VALUE,Double.MAX_VALUE,1);

    OutputVariable tip = new OutputVariable("tip");

    OutputTerm small = new OutputTerm("small",10);
    OutputTerm normal = new OutputTerm("normal",15);
    OutputTerm large = new OutputTerm("large",20);

    FuzzyEngine engine = new FuzzyEngine(
      service.is(poor).then(tip.set(small)),
      or(service.is(excellent),food.is(excellent)).then(tip.set(large))


    );
    // END ENGINE INIT

    service.setValue(4);
    food.setValue(10);
    double result = engine.evaluate();
    System.out.println(result);
  }
}
