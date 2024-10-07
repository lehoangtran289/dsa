package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class _2696_E_MinStrLenAfterRemoveSubStr {
    public static void main(String[] args) {
//        System.out.println(new _2696_E_MinStrLenAfterRemoveSubStr().minLength("AATQCABDCBE"));
        System.out.println(new _2696_E_MinStrLenAfterRemoveSubStr().minLength("ACBBD"));
    }

    public int minLength(String s) {
        int count = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            int cur = s.charAt(i);
            if (cur < 'A' || cur > 'D') {
                count += 1 + stack.size();
                stack = new ArrayDeque<>();
            }

            if (cur == 'A' || cur == 'C') {
                stack.addLast(cur);
            } else if (cur == 'B' || cur == 'D') {
                if (stack.isEmpty()) count ++;
                else {
                    if (cur - stack.peekLast() == 1) {
                        stack.pollLast();
                    } else {
                        count += 1 + stack.size();
                        stack = new ArrayDeque<>();
                    }
                }
            }
        }
        return count + stack.size();
    }
}
