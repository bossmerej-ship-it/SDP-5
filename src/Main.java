import assignment5.AddStrategy;
import assignment5.Calculator;
import assignment5.DivideStrategy;
import assignment5.MultiplyStrategy;
import assignment5.OperationStrategy;
import assignment5.SubtractStrategy;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, OperationStrategy> OPERATIONS = Map.of(
        "1", new AddStrategy(),
        "2", new SubtractStrategy(),
        "3", new MultiplyStrategy(),
        "4", new DivideStrategy()
    );

    public static void main(String[] args) {
        Calculator calculator = new Calculator(OPERATIONS.get("1"));
        if (args.length == 1 && "--demo".equals(args[0])) {
            runDemo(calculator);
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Add\n2. Subtract\n3. Multiply\n4. Divide\n0. Exit");
                System.out.print("Choose an operation: ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String choice = scanner.nextLine().trim();
                if ("0".equals(choice)) {
                    break;
                }
                OperationStrategy strategy = OPERATIONS.get(choice);
                if (strategy == null) {
                    System.out.println("Invalid choice. Enter 0, 1, 2, 3, or 4.");
                    continue;
                }
                calculator.setStrategy(strategy);
                Double first = readNumber(scanner, "First number: ");
                if (first == null) {
                    break;
                }
                Double second = readNumber(scanner, "Second number: ");
                if (second == null) {
                    break;
                }
                try {
                    double result = calculator.calculate(first, second);
                    if (Double.isFinite(result)) {
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("Error: Result is outside the finite double range.");
                    }
                } catch (IllegalArgumentException exception) {
                    System.out.println("Error: " + exception.getMessage());
                }
            }
        }
        System.out.println("Goodbye.");
    }

    private static Double readNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return null;
            }
            try {
                double number = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isFinite(number)) {
                    return number;
                }
            } catch (NumberFormatException exception) {
                // Invalid input is handled by the same message below.
            }
            System.out.println("Invalid number. Enter a finite number.");
        }
    }

    private static void runDemo(Calculator calculator) {
        String[] symbols = {"+", "-", "*", "/"};
        for (int index = 0; index < symbols.length; index++) {
            calculator.setStrategy(OPERATIONS.get(Integer.toString(index + 1)));
            System.out.println("10.0 " + symbols[index] + " 5.0 = " + calculator.calculate(10.0, 5.0));
        }
    }
}
