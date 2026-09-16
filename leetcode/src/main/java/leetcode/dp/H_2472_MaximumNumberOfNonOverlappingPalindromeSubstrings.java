package leetcode.dp;

public class H_2472_MaximumNumberOfNonOverlappingPalindromeSubstrings {
    static void main() {
        System.out.println(maxPalindromes("abaccdbbd", 3)); // 2
    }

    /**
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int maxPalindromes(String s, int k) {
        int n = s.length();

        // build palindrome checker, palindrome[i][j] = if s[i..j] is palindrome
        boolean[][] isPalindrome = new boolean[n][n];
        for (int len = 1; len <= n; ++len) {
            for (int i = 0; i < n - len + 1; ++i) {
                int j = i + len - 1;

                isPalindrome[i][j] = s.charAt(i) == s.charAt(j)
                                     && (len <= 2 || isPalindrome[i + 1][j - 1]);
            }
        }

        int[] dp = new int[n + 1]; // max palindrome substring using first i-th chars
        for (int j = 1; j <= n; ++j) {
            dp[j] = dp[j - 1]; // skip

            for (int i = 0; i <= j - k; ++i) {
                if (isPalindrome[i][j - 1])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }
        return dp[n];
    }
}
