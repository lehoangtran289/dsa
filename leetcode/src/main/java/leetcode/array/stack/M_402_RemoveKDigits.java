package leetcode.array.stack;

import java.util.LinkedList;

public class M_402_RemoveKDigits {
    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3)); // "1219"
        System.out.println(removeKdigits("10200", 1)); // "200"
        System.out.println(removeKdigits("10", 2)); // "0"
    }

    /**
     * Monotonic Stack
     * Idea:
     * - Use a stack to build the smallest number by removing k digits
     * - Pop from stack when current digit is smaller than stack top and we still have removals left
     * - After processing all digits, if we still have removals left, remove from the end
     * - Finally, build the result string while skipping leading zeros
     * ---------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        int ops = 0;

        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peekLast() > c && ops < k) {
                stack.removeLast();
                ops++;
            }
            stack.addLast(c);
        }

        // remove leading digits base on remaining ops
        while (!stack.isEmpty() && ops < k) {
            stack.removeLast();
            ops++;
        }

        // build result in reverse order of stack
        StringBuilder res = new StringBuilder();
        boolean isLeadingZeros = true;

        for (char digit : stack) {
            if (digit == '0' && isLeadingZeros) continue;
            isLeadingZeros = false;
            res.append(digit);
        }

        return res.length() == 0 ? "0" : res.toString();
    }
}
