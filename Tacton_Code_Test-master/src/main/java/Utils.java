import java.util.Stack;

public interface Utils {
    /**
     * This method will compute the value
     * To calculate the sequence we have to call compute method and pass sequence as param
     *
     * @param sequence
     * @return Calculated Value
     * @throws CustomException
     */
    public double compute(String sequence) throws CustomException;

    /**
     * Identify Positive and negative number
     *
     * @param sequence
     * @param offset
     * @return If the operator returns true, otherwise it returns false
     */
    public int parseNumber(String sequence, int offset);

    /**
     * Determine whether it is an operator
     *
     * @param character
     * @return If the operator returns true, otherwise it returns false
     */
    public boolean isOperator(char character);

    /**
     * Determine which operator
     *
     * @param sequence
     * @param offset
     * @return Operator ENUM
     */
    public Operator parseOperator(String sequence, int offset);

    /**
     * As per the priority this method will re arrange and calculate the value
     * @param numberStack
     * @param operatorStack
     * @param futureTop
     */
    public void collapseTop(Stack<Double> numberStack, Stack<Operator> operatorStack, Operator futureTop);

    /**
     * Judging the priority of operators
     *
     * @param op
     * @return The return priority is 1, 2, 0, respectively
     */
    public int priorityOfOperator(Operator op);

    /**
     * Apply the Operators and calculate the values
     *
     * @param left
     * @param op
     * @param right
     * @return The return of calculated Value
     */
    public double applyOperator(double left, Operator op, double right);


}
