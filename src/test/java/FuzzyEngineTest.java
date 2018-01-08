import me.thebutlah.fuzzywuzzy.*;
import org.junit.Test;


import static me.thebutlah.fuzzywuzzy.FuzzyLogic.*;

/**
 * Created by Ryan on 1/29/2017.
 */
public class FuzzyEngineTest {

    @Test
    public void testSingleOutput1() {

        //BEGIN INITIALIZING ENGINE
        FuzzyEngine engine = new FuzzyEngine();
        //Each InputVariable will be used to pass info into the FuzzyEngine
        InputVariable service = engine.addInputVariable("service");
        InputVariable food = engine.addInputVariable("food");

        InputTerm poor = new InputTerm("poor", 0, 1, 3, 4);
        InputTerm average = new InputTerm("average", 3, 4, 6, 7);
        InputTerm excellent = new InputTerm("excellent", 6, 7, Double.MAX_VALUE, Double.MAX_VALUE);

        //Each OutputVariable will be used to get the results from the FuzzyEngine
        OutputVariable tip = engine.addOutputVariable("tip");

        OutputTerm small = new OutputTerm("small", 10);
        OutputTerm normal = new OutputTerm("normal", 15);
        OutputTerm large = new OutputTerm("large", 20);

        engine.addFuzzyRules(
            //FuzzyRule #1: IF service is poor THEN tip is small
            service.is(poor).then(tip.set(small)),

            //FuzzyRule #2: IF service is excellent OR food is excellent THEN tip is large
            or(service.is(excellent), food.is(excellent)).then(tip.set(large))
        );
        // END ENGINE INIT

        service.setValue(2);
        food.setValue(5);
        engine.defuzzify();
        System.out.println(engine.describeRules());
        System.out.printf("Tip: %f\n", tip.getValue());
    }
}
