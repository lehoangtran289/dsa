package leetcode.string;

import java.util.Stack;

public class M_856_ScoreOfParentheses {

    static void main() {
        System.out.println(scoreOfParentheses("((())())()")); // 7
    }

    /**
     * Idea: recursively calculate score for each part
     * ---
     * TC: O(n) - traverse once
     * SC: O(n) - stack + recursion
     */
    public static int scoreOfParentheses(String s) {
        int n = s.length();
        int[] closeIndex = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                closeIndex[stack.pop()] = i;
            }
        }

        return solve(0, n - 1, closeIndex);
    }

    private static int solve(int start, int end, int[] closeIndex) {
        if (start > end) return 0;

        int score;
        int close = closeIndex[start];

        if (close == start + 1) {
            score = 1;
        } else {
            score = 2 * solve(start + 1, close - 1, closeIndex);
        }

        return score + solve(close + 1, end, closeIndex);
    }
}
