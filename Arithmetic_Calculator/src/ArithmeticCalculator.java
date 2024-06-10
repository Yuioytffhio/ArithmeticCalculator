
/**
 * The ArithmeticCalculator class performs arithmetic calculations and supports the following operands:
 * +, -, *, /, ^, >, <, >=, <=, ==, and !=.
 */
public class ArithmeticCalculator {

    /**
     * Evaluates a given arithmetic expression and returns the result
     * @param str The arithmetic expression to evaluate
     * @return The result of the arithmetic expression
     */
    public static int arithmeticCalculator(String str) {
        Stack<String> operatorStack = new Stack<>();
        Stack<Integer> operandStack = new Stack<>();
        int n = str.length();
        int i = 0;

        while (i < n) {
            char c = str.charAt(i);

            // Skip spaces
            if (c == ' ') {
                i++;
                continue;
            }

            // Process numbers
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < n && Character.isDigit(str.charAt(i))) {
                    num = num * 10 + (str.charAt(i) - '0');
                    i++;
                }
                operandStack.push(num);
                continue;
            }

            // Process opening parenthesis
            if (c == '(') {
                operatorStack.push(String.valueOf(c));
            }
            // Process closing parenthesis
            else if (c == ')') {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.pop(); // Remove '('
            }
            // Process operators
            else if (isOperator(String.valueOf(c))) {
                String operator = String.valueOf(c);

                /*
                if (isOperator(String.valueOf(str.charAt(i + 1)))) {
                    operator += str.charAt(i + 1);
                    i = i + 2;
                }
                 */

                if (i + 1 < n) {
                    String potentialOperator = operator + str.charAt(i + 1);
                    if (isMultiCharOperator(potentialOperator)) {
                        operator = potentialOperator;
                        i++;
                    }
                }

                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(operator)) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.push(operator);
            }
            i++;
        }

        while (!operatorStack.isEmpty()) {
            performOperation(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    /**
     * Performs an operation using the top operator and operands
     * @param operandStack The stack of operands
     * @param operatorStack The stack of operators
     */
    public static void performOperation(Stack<Integer> operandStack, Stack<String> operatorStack) {
        if (operandStack.getSize() < 2) {
            throw new IllegalStateException("Operand stack underflow");
        }
        int rightOperand = operandStack.pop();
        int leftOperand = operandStack.pop();
        String operator = operatorStack.pop();
        int result = applyOperator(leftOperand, rightOperand, operator);
        operandStack.push(result);
    }

    /**
     * Applies an operator to two operands and returns the result
     * @param leftOperand The left operand
     * @param rightOperand The right operand
     * @param operator The operator to apply
     * @return The result
     */
    public static int applyOperator(int leftOperand, int rightOperand, String operator) {
        switch (operator) {
            case "+": return leftOperand + rightOperand;
            case "-": return leftOperand - rightOperand;
            case "*": return leftOperand * rightOperand;
            case "/": return leftOperand / rightOperand;
            case "^": return (int) Math.pow(leftOperand, rightOperand);
            case ">": return leftOperand > rightOperand ? 1 : 0;
            case "<": return leftOperand < rightOperand ? 1 : 0;
            case ">=": return leftOperand >= rightOperand ? 1 : 0;
            case "<=": return leftOperand <= rightOperand ? 1 : 0;
            case "!=": return leftOperand != rightOperand ? 1 : 0;
            case "==": return leftOperand == rightOperand ? 1 : 0;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    /**
     * Determines the precedence of an operator
     * @param operator The operator whose precedence is to be determined
     * @return The precedence of the operator
     */
    public static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            case ">", "<", ">=", "<=" -> 4;
            case "==", "!=" -> 5;
            default -> 0;
        };
    }

    /**
     * Determines if a string is a recognized operator
     * @param c The string to check
     * @return True if the string is an operator, false otherwise
     */
    public static boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^") || c.equals("=") || c.equals("!")
                || c.equals(">") || c.equals("<") || c.equals(">=") || c.equals("<=") || c.equals("!=") || c.equals("==");
    }

    /**
     * Determines if a string is a multi-character operator
     * @param c The string to check
     * @return True if the string is a multi-character operator, false otherwise
     */
    public static boolean isMultiCharOperator(String c) {
        return c.equals(">=") || c.equals("<=") || c.equals("!=") || c.equals("==");
    }
}