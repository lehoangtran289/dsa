package leetcode.array.stack;

import java.util.Stack;

public class M_150_EvaluateReversePolishNotation {
    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"})); // 9
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"})); // 6
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "/", "-", "*"})); // 30
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!isExpression(token)) {
                stack.push(Integer.parseInt(token));
                continue;
            }

            int n1 = stack.pop();
            int n2 = stack.pop();

            if (token.equals("+")) {
                stack.push(n1 + n2);
            } else if (token.equals("-")) {
                stack.push(n2 - n1);
            } else if (token.equals("*")) {
                stack.push(n1 * n2);
            } else if (token.equals("/")) {
                stack.push(n2 / n1);
            }
        }

        return stack.peek();
    }

    private static boolean isExpression(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
}
