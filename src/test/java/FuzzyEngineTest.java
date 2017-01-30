import me.thebutlah.fuzzywuzzy.*;
import me.thebutlah.fuzzywuzzy.rules.FuzzyLogic;
import me.thebutlah.fuzzywuzzy.rules.FuzzyRule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 1/29/2017.
 */
public class FuzzyEngineTest {

  @Test
  public void test() {
    InputVariable service = new InputVariable("service");
    InputVariable food = new InputVariable("food");

    InputTerm poor = new InputTerm("poor", 0,1,3,4,1);
    InputTerm average = new InputTerm("average", 3,4,6,7,1);
    InputTerm excellent = new InputTerm("excellent", 6,7,9,10,1);

    OutputVariable tip = new OutputVariable("tip");

    OutputTerm small = new OutputTerm("small",10);
    OutputTerm normal = new OutputTerm("normal",15);
    OutputTerm large = new OutputTerm("large",20);

    FuzzyEngine engine = new FuzzyEngine();
    engine.addRules(
      service.is(poor).then(tip.set(small))


    );
    double result = engine.evaluate();
    System.out.println(result);
  }
}
