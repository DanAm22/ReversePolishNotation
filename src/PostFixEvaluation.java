package rpn;

import java.util.Deque;
import java.util.LinkedList;

public class PostFixEvaluation {

    // this method verify if the String is an operator (excluding parentheses)
    public static boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^");
    }

    // Method for getting the result according to ops and operator
    public static int makeCalculus(int op1, int op2, String operator){
        switch (operator){
            case "+":
                return op2 + op1;
            case "-":
                return op2 - op1;
            case "*":
                return op2 * op1;
            case "/":
                return op2 / op1;
            case "^":
                return (int) Math.pow(op2,op1);
        }
        return -1;
    }

    // RPN algoritm for an array of Strings
    public static int rpnVal(String[] tokens) {
        int rpnVal = 0;
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < tokens.length; i++) {

            if (!isOperator(tokens[i])) {
                stack.push(Integer.parseInt(tokens[i]));
            }
            else{
                int op1 = stack.pop();
                int op2 = stack.pop();
                int result = makeCalculus(op1,op2,tokens[i]);
                stack.push(result);
            }
        }

        rpnVal = stack.pop();

        if(!stack.isEmpty()){
            System.out.println("Error. Wrong input");
            return -1;
        }

        return rpnVal;
    }

    // Testing the Algoritm
    public static void main(String[] args) {
        String[] tokens = {"4","13","5","/","+"};
        int rpnValue = rpnVal(tokens);
        System.out.println(rpnValue);
    }
}
