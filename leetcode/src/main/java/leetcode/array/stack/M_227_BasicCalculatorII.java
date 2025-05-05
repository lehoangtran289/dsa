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
        Stack<Integer> stack = new Stack<>();
        int curNum = 0;
        char prev = '+';

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (isDigit(c)) {
                curNum = curNum * 10 + (c - '0');
            }

            if ((!isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (prev == '+') {
                    stack.push(curNum);
                } else if (prev == '-') {
                    stack.push(-curNum);
                } else if (prev == '*') {
                    stack.push(stack.pop() * curNum);
                } else if (prev == '/') {
                    stack.push(stack.pop() / curNum);
                }
                prev = c;
                curNum = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
