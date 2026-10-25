package leetcode.dp;

public class M_647_PalindromicSubstrings {

    /**
     * Build a matrix boolean[][] isPalindrome to check if s[i:j] is palindrome using dp
     * Use 2 for loops for traverse over all substring and check if it is palindrome
     * ---
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        // len = 1
        for (int i = 0; i < n; ++i) {
            isPalindrome[i][i] = true;
        }

        // len = 2
        for (int i = 0; i < n - 1; ++i) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPalindrome[i][i + 1] = true;
            }
        }

        // len >= 3
        for (int len = 3; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) && isPalindrome[i + 1][j - 1]) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (isPalindrome[i][j]) res++;
            }
        }
        return res;
    }

    /**
     * Same idea. But 1 pass only
     */
    public int countSubstrings2(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int res = 0;

        for (int len = 1; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;

                isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && (len <= 2 || isPalindrome[i + 1][j - 1]);
                if (isPalindrome[i][j]) res++;
            }
        }

        return res;
    }
}
