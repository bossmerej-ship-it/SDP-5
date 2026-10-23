package assignment5;

public class DivideStrategy implements OperationStrategy {
    @Override
    public double execute(double first, double second) {
        if (second == 0.0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        return first / second;
    }
}
