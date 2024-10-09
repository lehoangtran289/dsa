package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class _E_2696_MinStrLenAfterRemoveSubStr {
    public static void main(String[] args) {
//        System.out.println(new _E_2696_MinStrLenAfterRemoveSubStr().minLength("AATQCABDCBE"));
        System.out.println(new _E_2696_MinStrLenAfterRemoveSubStr().minLength("ACBBD"));
    }

    /**
     *     def minLength(self, s: str) -> int:
     *         stack = []
     *         for c in s:
     *             if not stack:
     *                 stack.append(c)
     *                 continue
     *             if c == "B" and stack[-1] == "A":
     *                 stack.pop()
     *             elif c == "D" and stack[-1] == "C":
     *                 stack.pop()
     *             else:
     *                 stack.append(c)
     *         return len(stack)
     */
    public int minLength(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.addLast(c);
                continue;
            }

            if (c == 'B' && stack.peekLast() == 'A') {
                stack.pollLast();
            } else if (c == 'D' && stack.peekLast() == 'C') {
                stack.pollLast();
            } else {
                stack.addLast(c);
            }
        }
        return stack.size();
    }
}
