public class Main {
    public static void main(String[] args) throws CustomException {
        String expression1 = "2 + 3";
        String expression2 = "3 * 2 + 1";
        String expression3 = "3 * -2 + 6 ";
        Calculator calculator = new Calculator();
        System.out.println(expression1 + " = " + calculator.compute(expression1));
        System.out.println(expression2 + " = " + calculator.compute(expression2));
        System.out.println(expression3 + " = " + calculator.compute(expression3));


    }
}
