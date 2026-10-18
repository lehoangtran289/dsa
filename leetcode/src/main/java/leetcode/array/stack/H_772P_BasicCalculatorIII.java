package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class H_772P_BasicCalculatorIII {

    /**
     * Idea: stack + recursion
     ---
     Dry run: 2*(5+5*2)/3+(6/2+8)
     i = 0 -> num = 2, sign = +, stack = []
     i = 1 -> num = 0, sign = *, stack = [2]
     i = 2 -> solve(s, 3)
     i = 3 -> num = 5, sign = +, stack = []
     i = 4 -> num = 0, sign = +, stack = [5]
     i = 5 -> num = 5, sign = +, stack = [5]
     i = 6 -> num = 0, sign = *, stack = [5, 5]
     i = 7 -> num = 2, sign = *, stack = [5, 5]
     i = 8 -> ) -> num = 0, sign = ), stack = [5, 10]
     -> [15, 8]
     i = 9
     */
    public int calculate(String s) {
        return solve(s, 0)[0];
    }

    private int[] solve(String s, int start) {
        Deque<Integer> stack = new ArrayDeque<>();
        int number = 0;
        char sign = '+';
        int closeIndex = -1;

        for (int i = start; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') continue;

            if (c == '(') {
                int[] subExpr = solve(s, i + 1);
                number = subExpr[0];
                i = subExpr[1];
                continue;
            }

            if (c == ')') {
                closeIndex = i;
                break;
            }

            if (isDigit(c)) {
                number = number * 10 + (c - '0');
                continue;
            }

            // when c = sign
            pushStack(stack, sign, number);
            number = 0;
            sign = c;
        }

        // handle last number in expr
        pushStack(stack, sign, number);

        int result = 0;
        while (!stack.isEmpty()) result += stack.pop();
        return new int[]{result, closeIndex};
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
