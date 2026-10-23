package assignment5;

public class SubtractStrategy implements OperationStrategy {
    @Override
    public double execute(double first, double second) {
        return first - second;
    }
}
