package assignment5;

import java.util.Objects;

public class Calculator {
    private OperationStrategy strategy;

    public Calculator(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy cannot be null.");
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy cannot be null.");
    }

    public double calculate(double first, double second) {
        return strategy.execute(first, second);
    }
}
