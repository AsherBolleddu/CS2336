/*
* Name: Asher Bolleddu
* Date: 11/10/2022
* Class: CS2336.504
*
* The program takes in an infix expression, converts it into a postfix expression, and then evaluates the postfix expression.
* */

import java.util.Stack;
import java.util.regex.Pattern;
import java.util.Scanner;

public class EvaluateExpression {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an infix expression: ");
        String expression = scanner.nextLine();
        System.out.println("The evaluated postfix expression: " + evaluateExpression(expression));
    }

    /** Evaluate an expression */
    public static double evaluateExpression(String expression) {
        // Create operandStack to store operands
        Stack<Double> operandStack = new Stack<>();

        // Create operatorStack to store operators
        Stack<Character> operatorStack = new Stack<>();

        // Insert blanks around (, ), +, -, /, and *
        expression = insertBlanks(expression);

        // Extract operands and operators
        String[] tokens = expression.split(" ");

        // Phase 1: Scan tokens
        for (String token: tokens) {
            if (token.length() == 0) { // Blank space
                continue; // Back to the while loop to extract the next token

            }
            else if (token.equals("+") || token.equals("-")) {
                // Process all +, -, *, / in the top of the operator stack
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '+' || operatorStack.peek() == '-' || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the + or - operator into the operator stack
                operatorStack.push(token.charAt(0));
            }
            else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                // Process all *, / in the top of the operator stack
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the * or / operator into the operator stack
                operatorStack.push(token.charAt(0));
            }
            else if (token.trim().charAt(0) == '(') {
                operatorStack.push('('); // Push '(' to stack
            }
            else if (token.trim().charAt(0) == ')') {
                // Process all the operators in the stack until seeing '('
                while (operatorStack.peek() != '(') {
                    processAnOperator(operandStack, operatorStack);
                }

                operatorStack.pop(); // Pop the '(' symbol from the stack
            }
            else { // An operand scanned
                // Push an operand to the stack
                operandStack.push(new Double(token));
            }
        }

        // Phase 2: process all the remaining operators in the stack
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        // Return the result
        return operandStack.pop();
    }

    /** Process one operator: Take an operator from operatorStack and
     *  apply it on the operands in the operandStack */
    public static void processAnOperator(Stack<Double> operandStack, Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        double op1 = operandStack.pop();
        double op2 = operandStack.pop();
        if (op == '+')
            operandStack.push(op2 + op1);
        else if (op == '-')
            operandStack.push(op2 - op1);
        else if (op == '*')
            operandStack.push(op2 * op1);
        else if (op == '/')
            operandStack.push(op2 / op1);
    }

    // Inserts blanks (aka whitespace) into the infix expression
    public static String insertBlanks(String s) {
        StringBuilder result = new StringBuilder();

        // Case when the first character is a '-'
        if (s.charAt(0) == '-'){
            result.append(s.charAt(0));
        }

        // Case when the first character is a '('
        if (s.charAt(0) == '('){
            result.append(s.charAt(0)).append(" ");
        }

        // Case when the first character is a number
        if (isNumeric(String.valueOf(s.charAt(0)))){
            result.append(s.charAt(0)).append(" ");
        }

        for (int i = 1; i < s.length(); i++) {

            // Checks to see if the '-' character is going to be a subtraction or a negative. If the character before the '-' is an operator, then the following has to be a negative number.
            if (s.charAt(i) == '-' && isOperator(s.charAt(i-1))){
                result.append(s.charAt(i));
            }
            // Checks to see if it's an operator and appends it and adds a space
            else if (s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/'){
                result.append(" ").append(s.charAt(i)).append(" ");
            }
            // Appends the number to result
            else{
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    // Checks if the character is an operator
    public static boolean isOperator(char ch){
        return ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static boolean isNumeric(String string){
        // Checks if the provided string
        // is a numeric by applying a regular
        // expression on it.
        String regex = "[0-9]+[\\.]?[0-9]*";
        return Pattern.matches(regex, string);
    }
}