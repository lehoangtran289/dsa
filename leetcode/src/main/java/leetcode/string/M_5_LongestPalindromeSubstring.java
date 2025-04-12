package leetcode.string;

public class M_5_LongestPalindromeSubstring {
    public static void main(String[] args) {
        System.out.println(new M_5_LongestPalindromeSubstring().longestPalindrome2(
                "aaaaa"
        )); // "aaaaa"
    }

    /**
     * -----------------------------------------------
     * Brute force approach
     * Check all substrings, starting from the longest
     * -----------------------------------------------
     * TC O(n^3)
     * SC O(1)
     */
    public String longestPalindrome(String s) {
        for (int length = s.length(); length > 0; length--) {
            for (int i = 0; i <= s.length() - length; i++) {
                String cur = s.substring(i, i + length);
                if (isPalindrome(cur)) {
                    return cur;
                }
            }
        }

        return "";
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    /**
     * -----------------------------------------------
     * Bottom up DP approach
     * -
     * dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1]
     * -----------------------------------------------
     * TC O(n^2)
     * SC O(n^2)
     */
    public String longestPalindrome2(String s) {
        int n = s.length();
        int start = 0, end = 0;
        boolean[][] dp = new boolean[n][n];

        // base case
        // 1 or 2 chars
        for (int i = 0; i < n; ++i) {
            dp[i][i] = true;

            if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                end = i + 1;
            }
        }

        // bottom up dp
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (
                        s.charAt(i) == s.charAt(j) &&
                        dp[i + 1][j - 1]
                ) {
                    dp[i][j] = true;
                    if (end - start < j - i) {
                        start = i;
                        end = j;
                    }
                }
            }
        }

        return s.substring(start, end);
    }
}
