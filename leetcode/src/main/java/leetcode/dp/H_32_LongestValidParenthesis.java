package leetcode.dp;

public class H_32_LongestValidParenthesis {
    static void main() {
        System.out.println(longestValidParentheses("(()())")); // 6
    }

    /**
     * Idea: dp[i] = length of longest valid parenthesis substring ending at index i
     * If s[i] == '(' -> dp[i] = 0
     * If s[i] == ')' -> dp[i] = s[i - 1] == '(' ? dp[i - 2] + 2 (e.g: "_()")
     *                           s[i - dp[i - 1] - 1] == '(' ? dp[i - dp[i - 1] - 2] + dp[i - 1] + 2 (e.g: "_(())")
     */
    public static int longestValidParentheses(String s) {
        int n = s.length();

        int[] dp = new int[n];
        int res = 0;

        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == '(') continue;

            if (s.charAt(i - 1) == '(') {
                dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
            } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = 2 + dp[i - 1]
                        + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
