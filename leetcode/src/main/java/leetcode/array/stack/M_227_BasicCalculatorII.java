package leetcode.array.stack;

import java.util.Stack;

public class M_227_BasicCalculatorII {

    /**
     * ----------------------------------------------------------------
     * Use stack to store intermediate results, then sum all elements in stack
     * If +/-: push curNum to stack
     * If * or /: pop the last element from stack, multiply/divide it with curNum and push the result back to stack
     * ----------------------------------------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    public int calculate(String s) {
        s += '+'; // handle last number, can be any sign
        Stack<Integer> stack = new Stack<>();
        int number = 0;
        char sign = '+';

        for (char c : s.toCharArray()) {
            if (c == ' ') continue;

            if (isDigit(c)) {
                number = number * 10 + (c - '0');
                continue;
            }

            if (sign == '+') {
                stack.push(number);
            } else if (sign == '-') {
                stack.push(-number);
            } else if (sign == '*') {
                stack.push(stack.pop() * number);
            } else if (sign == '/') {
                stack.push(stack.pop() / number);
            }
            number = 0;
            sign = c;
        }

        System.out.println(stack);

        int result = 0;
        while (!stack.isEmpty()) result += stack.pop();
        return result;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
