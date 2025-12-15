package leetcode.dp;

import java.util.Arrays;

public class M_72_EditDistance {
    /**
     * DP Top down with memoization
     * ----------------------------------
     * TC: O(m * n)
     * SC: O(m * n)
     */
    private int[][] memo;
    private String word1, word2;
    private int len1, len2;

    public static void main(String[] args) {
        System.out.println(new M_72_EditDistance().minDistance("horse", "ros")); // 3
    }

    /**
     * DP Bottom up
     * ----------------------------------
     * dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
     * Recurrence relation:
     * if word1[i-1] == word2[j-1]:
     * dp[i][j] = dp[i-1][j-1]
     * else:
     * dp[i][j] = 1 + min(
     * dp[i-1][j-1], // replace
     * dp[i-1][j],   // delete
     * dp[i][j-1]    // insert
     * )
     * ----------------------------------
     * TC: O(m * n)
     * SC: O(m * n)
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1]; // dp[i][j] = min ops convert word1[1..i] to word2[1..j]

        // base cases, when len1 = 0 or len2 = 0
        for (int i = 0; i <= len1; ++i) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; ++i) {
            dp[0][i] = i;
        }

        // dp
        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1], // replace
                            Math.min(
                                    dp[i - 1][j], // delete in word1
                                    dp[i][j - 1] // insert to word1
                            )
                    );
                }
            }
        }

        return dp[len1][len2];
    }

    public int minDistance2(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        this.len1 = word1.length();
        this.len2 = word2.length();

        this.memo = new int[len1 + 1][len2 + 1];
        for (int[] arr : memo) Arrays.fill(arr, -1);

        return dp(0, 0);
    }

    private int dp(int i, int j) {
        if (i == len1) return len2 - j;
        if (j == len2) return len1 - i;
        if (memo[i][j] != -1) return memo[i][j];

        if (word1.charAt(i) == word2.charAt(j)) {
            return memo[i][j] = dp(i + 1, j + 1); // skip, move to next pos
        } else {
            return memo[i][j] = 1 + Math.min(
                    dp(i + 1, j + 1), // replace, move to next pos
                    Math.min(
                            dp(i + 1, j), // delete pos i-th from word1, forward word1 to next pos
                            dp(i, j + 1) // insert to pos (i-1)-th of word1, forward word2 to next pos
                    )
            );
        }
    }
}
