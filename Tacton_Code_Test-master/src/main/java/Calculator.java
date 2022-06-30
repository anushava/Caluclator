import java.util.Stack;

public class Calculator implements Utils {
    public double compute(String sequence) throws CustomException {
        //Create a stack of numbers
        Stack<Double> numberStack = new Stack<Double>();
        //Create a symbol stack
        Stack<Operator> operatorStack = new Stack<Operator>();
        // Removing the blank space
        sequence = sequence.replaceAll("\\s", "");
        // Loop the sequence and identify the numbers and operators
        for (int i = 0; i < sequence.length(); i++) {
            try {
                // Calling the parseNumber function and get the number
                int number = parseNumber(sequence, i);
                // Add the number to Stack
                numberStack.push((double) number);
                //Validate length of the number and increase index accordingly
                i += Integer.toString(number).length();
                if (i >= sequence.length()) {
                    break;
                }
                // Validate the operator with parseOperator method
                Operator op = parseOperator(sequence, i);
                // prioritize the calculations
                collapseTop(numberStack, operatorStack, op);
                // Add the Operator to Stack
                operatorStack.push(op);

            } catch (Exception e) {
                throw new CustomException("Exception due to : " + e.getMessage());
            }
        }
        collapseTop(numberStack, operatorStack, Operator.BLANK);
        if (numberStack.size() == 1 && operatorStack.size() == 0) {
            return numberStack.pop().intValue();
        }
        return 0.0;
    }

    public int parseNumber(String sequence, int offset) {
        StringBuilder sb = new StringBuilder();
        // Validate the negative operator
        if (offset >= 1 && isOperator(sequence.charAt(offset - 1)) && isOperator(sequence.charAt(offset))) {
            sb.append(sequence.charAt(offset));
            offset++;
        }

        // Get the numbers until next operator
        while (offset < sequence.length() && Character.isDigit(sequence.charAt(offset))) {
            sb.append(sequence.charAt(offset));
            offset++;
        }
        return Integer.parseInt(sb.toString());
    }

    public boolean isOperator(char character) {
        // Validate the operator with symbols
        return character == '+' || character == '-' || character == '*' || character == '/';
    }

    public Operator parseOperator(String sequence, int offset) {
        // Validate the operator with symbols
        // Assigning ENUMS
        if (offset < sequence.length()) {
            char op = sequence.charAt(offset);
            switch (op) {
                case '+':
                    return Operator.ADD;
                case '-':
                    return Operator.SUBTRACT;
                case '*':
                    return Operator.MULTIPLY;
                case '/':
                    return Operator.DIVIDE;
            }
        }
        return Operator.BLANK;
    }

    public void collapseTop(Stack<Double> numberStack, Stack<Operator> operatorStack, Operator futureTop) {
        // Loop until the stack numberStack size 2 and operatorStack size 1
        while (numberStack.size() >= 2 && operatorStack.size() >= 1) {
            // Prioritize the calculations using priorityOfOperator method
            if (priorityOfOperator(futureTop) <= priorityOfOperator(operatorStack.peek())) {
                double second = numberStack.pop();
                double first = numberStack.pop();
                Operator op = operatorStack.pop();
                double result = applyOperator(first, op, second);
                numberStack.push(result);
            } else {
                break;
            }
        }
    }

    public int priorityOfOperator(Operator op) {
        // Validate the Operators
        switch (op) {
            case ADD:
            case SUBTRACT:
                return 1;
            case MULTIPLY:
            case DIVIDE:
                return 2;
            case BLANK:
                return 0;
        }
        return 0;
    }

    public double applyOperator(double left, Operator op, double right) {
        // Validate the Operators and do the calculation
        switch (op) {
            case ADD:
                return left + right;
            case SUBTRACT:
                return left - right;
            case MULTIPLY:
                return left * right;
            case DIVIDE:
                return left / right;
            default:
                return right;
        }
    }

}
