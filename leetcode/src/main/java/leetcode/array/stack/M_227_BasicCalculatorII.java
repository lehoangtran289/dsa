package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;

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
        Deque<Integer> stack = new ArrayDeque<>();
        int number = 0;
        char sign = '+';

        for (char c : s.toCharArray()) {
            if (c == ' ') continue;

            if (isDigit(c)) {
                number = number * 10 + (c - '0');
                continue;
            }

            // when c = sign
            pushStack(stack, sign, number);
            number = 0;
            sign = c;
        }

        // handle last number
        pushStack(stack, sign, number);

        int result = 0;
        while (!stack.isEmpty()) result += stack.pop();
        return result;
    }

    private void pushStack(Deque<Integer> stack, char sign, int number) {
        if (sign == '+') {
            stack.push(number);
        } else if (sign == '-') {
            stack.push(-number);
        } else if (sign == '*') {
            stack.push(stack.pop() * number);
        } else {
            stack.push(stack.pop() / number);
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
