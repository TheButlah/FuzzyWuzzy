import me.thebutlah.fuzzywuzzy.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import static me.thebutlah.fuzzywuzzy.FuzzyLogic.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ryan on 1/29/2017.
 */
public class FuzzyEngineTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testSingleOutput1() {

        //BEGIN INITIALIZING ENGINE
        FuzzyEngine engine = new FuzzyEngine("Tipping Engine");
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

        engine.setFuzzyRules(
            //FuzzyRule #1: IF service is poor THEN tip is small
            service.is(poor).then(tip.set(small)),

            //FuzzyRule #2: IF service is excellent OR food is excellent THEN tip is large
            or(service.is(excellent), food.is(excellent)).then(tip.set(large))
        );
        // END ENGINE INIT

        //Here, rule #1 fires fully.
        service.setValue(2);
        food.setValue(5);
        engine.defuzzify();
        System.out.println(engine.describeRules());
        System.out.printf("Service: %.3f, Food: %.3f => Tip: %.3f\n",
            service.getValue(), food.getValue(), tip.getValue());
        assertEquals(tip.getValue(), 10, DELTA);

        //Lies in similar regions on all input variables, so output should be exactly the same as the previous values.
        service.setValue(3);
        food.setValue(6);
        engine.defuzzify();
        System.out.printf("Service: %.3f, Food: %.3f => Tip: %.3f\n",
            service.getValue(), food.getValue(), tip.getValue());
        assertEquals(tip.getValue(), 10, DELTA);


        //Now, Rule #1 fires at 0.5 and rule #2 fires at 0.5. The average ends up smack between them as a compromise.
        service.setValue(3.5);
        food.setValue(6.5);
        engine.defuzzify();
        System.out.printf("Service: %.3f, Food: %.3f => Tip: %.3f\n",
            service.getValue(), food.getValue(), tip.getValue());
        assertEquals(tip.getValue(), 15, DELTA);

        //Here, no rules should fire so the output variable should be NaN.
        //The user should detect the NaN and decide what to do (often treat the NaN as the default case for the logic).
        food.setValue(0);
        service.setValue(0);
        engine.defuzzify();
        System.out.printf("Service: %.3f, Food: %.3f => Tip: %.3f\n",
            service.getValue(), food.getValue(), tip.getValue());
        assertTrue(Double.isNaN(tip.getValue()));
    }
}
