# Assignment 5 - Strategy Pattern

Option B: Calculator. Java 17.

Student: Kuatbay Merey  
Group: SE-2537

[GitHub repository](https://github.com/bossmerej-ship-it/SDP-5)

Seven Java files, no external libraries:

- `src/Main.java` - console menu and runtime strategy selection.
- `src/assignment5/Calculator.java` - context that delegates to the selected strategy.
- `OperationStrategy.java` - common arithmetic interface.
- `AddStrategy.java`, `SubtractStrategy.java`, `MultiplyStrategy.java`, `DivideStrategy.java` - interchangeable operations.

## Build and run

Open the project in IntelliJ IDEA, choose JDK 17, mark `src` as Sources Root, and run `Main.java`.

Or run these commands from the repository root (PowerShell, Command Prompt, or a Unix shell):

```text
javac --release 17 -Xlint:all -Werror -d out src/Main.java src/assignment5/*.java
java -cp out Main
```

Choose 1 = add, 2 = subtract, 3 = multiply, or 4 = divide, then enter two numbers. Repeat to change the strategy on the same calculator. Choose 0 to exit. Use a dot for decimals. Invalid input is requested again; division by positive or negative zero shows an error and returns to the menu. EOF exits cleanly.

To demonstrate all four strategies without typing:

```text
java -cp out Main --demo
```

```text
10.0 + 5.0 = 15.0
10.0 - 5.0 = 5.0
10.0 * 5.0 = 50.0
10.0 / 5.0 = 2.0
```

The calculator uses Java `double` arithmetic. Decimal rounding follows floating-point rules; this is not a financial calculator. The console accepts finite operands and reports a non-finite result as overflow. Validation branches handle input and errors; operation selection uses a map, and `Calculator` contains no strategy-type conditionals.

## Add a strategy

Create another class in `src/assignment5` implementing `OperationStrategy.execute(double first, double second)`. For example, `PowerStrategy` can return `Math.pow(first, second)`. The context, interface, and existing strategies stay unchanged.

Any client can use it immediately:

```java
calculator.setStrategy(new PowerStrategy());
double result = calculator.calculate(2.0, 3.0); // 8.0
```

To expose it in this console, add it to `Main.OPERATIONS` with a new key, update the displayed menu and choice message, and extend the demo if desired. Those are client configuration changes, not changes to the arithmetic context.

## Report and UML

[REPORT.md](REPORT.md) is the required six-section Markdown reflection for Moodle. [REPORT.pdf](REPORT.pdf) is a formatted reading copy with the same content. Both include six annotated Clean Code examples, runtime switching, validation, and Strategy versus State.

The [UML diagram](docs/strategy-uml.png) uses a hollow aggregation diamond at `Calculator`, dashed realization arrows pointing to `OperationStrategy`, and dashed client dependencies. [SVG](docs/strategy-uml.svg) and [PlantUML source](docs/strategy-uml.puml) are included.
