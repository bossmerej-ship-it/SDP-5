package assignment5;

public class AddStrategy implements OperationStrategy {
    @Override
    public double execute(double first, double second) {
        return first + second;
    }
}
