package rpn;

import java.util.Deque;
import java.util.LinkedList;

/* In this class, I've overloaded every method because I've delevoped two solutions depending on user's input
   The input can be a String or an array of Strings
 */

public class ShuntingYard {

    // this method calculates the priority of operator
    public static int priority(String c) {
        if (c.equals("+") || c.equals("-")) {
            return 11;
        } else if (c.equals("*") || c.equals("/")) {
            return 12;
        } else if (c.equals("^")) {
            return 13;
        } else return -1;
    }

    // this method calculates the priority of operator
    public static int priority(Character c) {
        if (c == '+' || c == '-') {
            return 11;
        } else if (c == '*' || c == '/') {
            return 12;
        } else if (c == '^') {
            return 13;
        } else return -1;
    }

    // this method verify if the String is an operator (excluding parentheses)
    public static boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^");
    }

    // this method verify if the char is an operator (excluding parentheses)
    public static boolean isOperator(Character c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    // shuntingYard algoritm for an array of Strings
    public static String shuntingYard(String[] tokens) {
        StringBuilder postFix = new StringBuilder();
        Deque<String> stack = new LinkedList<>();

        for (int i = 0; i < tokens.length; i++) {
            if (!isOperator(tokens[i]) && !tokens[i].equals("(") && !tokens[i].equals(")")) {
                postFix.append(Integer.parseInt(tokens[i]));
            } else if (isOperator(tokens[i])) {
                while (stack.peek() != null && isOperator(stack.peek()) && (priority(tokens[i]) < priority(stack.peek())
                        || (priority(tokens[i]) == priority(stack.peek())) && !stack.peek().equals("^"))) {
                    postFix.append(stack.pop());
                }
                stack.push(tokens[i]);
            } else if (tokens[i].equals("(")) {
                stack.push(tokens[i]);
            } else if (tokens[i].equals(")")) {
                while (!stack.peek().equals("(")) {
                    postFix.append(stack.pop());
                }
                if (stack.peek().equals("(")) {
                    stack.pop();
                } else {
                    return "Error. Wrong paranthesys input.";
                }
            }
        }

        while (!stack.isEmpty()) {
            if (!stack.peek().equals("(")) {
                postFix.append(stack.pop());
            } else {
                return "Error. Wrong paranthesys input.";
            }
        }

        return postFix.toString();
    }

    // shuntingYard algoritm for a String
    public static String shuntingYard(String input) {
        StringBuilder postFix = new StringBuilder();
        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            char entity = input.charAt(i);
            if (Character.isDigit(entity)) {
                postFix.append(entity);
            } else if (isOperator(entity)) {
                while (stack.peek() != null && isOperator(stack.peek()) && (priority(entity) < priority(stack.peek())
                        || (priority(entity) == priority(stack.peek())) && stack.peek() != '^')) {
                    postFix.append(stack.pop());
                }
                stack.push(entity);
            } else if (entity == '(') {
                stack.push(entity);
            } else if (entity == ')') {
                while (stack.peek() != '(') {
                    postFix.append(stack.pop());
                }
                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    return "Error. Wrong paranthesys input.";
                }
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() != '(') {
                postFix.append(stack.pop());
            } else {
                return "Error. Wrong paranthesys input.";
            }
        }

        return postFix.toString();
    }


    // Testing the Algoritm
    public static void main(String[] args) {
        String[] tokens = {"3","+","(","2","+","1",")","*","2","^","3","^","2","-","8","/","(","5","-","1","*","2","/","2",")"};
        String input = "3+(2+1)*2^3^2-8/(5-1*2/2)";
        String corect = "321+232^^*+8512*2/-/-";

        String resultTokens = shuntingYard(tokens);
        System.out.println(resultTokens);
        System.out.println(resultTokens.equals(corect));

        String result = shuntingYard(input);
        System.out.println(result);
        System.out.println(result.equals(corect));
    }
}
