package leetcode.array.stack;

import java.util.*;

public class M_1717_MaximumScoreFromRemovingSubstrings {
    public static void main(String[] args) {
        System.out.println(maximumGain("cdbcbbaaabab", 4, 5)); // Output: 19
        System.out.println(maximumGain("aabbaaxybbaabb", 5, 4)); // Output: 20
    }

    /**
     * 2 pass with Stack
     * 1st: remove all higher priority substring
     * 2nd: remove all lower priority substring
     * ---------------------
     * TC: O(N) - 2 passes
     * SC: O(N) - stack
     */
    public static int maximumGain(String s, int x, int y) {
        int res = 0;
        String higherPriorityStr = x > y ? "ab" : "ba";
        String lowerPriorityStr = higherPriorityStr.equals("ab") ? "ba" : "ab";

        String firstPass = removeSubstring(s, higherPriorityStr);
        res += ((s.length() - firstPass.length()) / 2) * Math.max(x, y);

        String secondPass = removeSubstring(firstPass, lowerPriorityStr);
        res += ((firstPass.length() - secondPass.length()) / 2) * Math.min(x, y);

        return res;
    }

    private static String removeSubstring(String s, String substr) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (
                    !stack.isEmpty() &&
                    stack.peek() == substr.charAt(0) &&
                    c == substr.charAt(1)
            ) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
