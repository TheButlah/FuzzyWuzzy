package me.thebutlah.fuzzywuzzy;

/**
 * Represents an output that an OutputVariable can take.
 *
 * Example: IF weather == cold THEN heat = high. "high" is the OutputTerm.
 *
 * @author Ryan Butler
 */
public class OutputTerm {

    private final String name;
    private final double value;

    /**
     * Constructs an OutputTerm object with a given value.
     * @param name The name of the OutputTerm.
     * @param value The value that a corresponding OutputVariable could take.
     */
    public OutputTerm(String name, double value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the name of the OutputTerm.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the OutputTerm.
     */
    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "OutputTerm{" + name + "," + value + "}";
    }
}
