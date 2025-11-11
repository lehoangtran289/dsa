package leetcode.dp;

import java.util.Arrays;

/**
 * Knapsack DP
 */
public class M_474_OnesAndZeroes {

    /**
     * Top Down Knapsack DP
     * ----------------------------------------------
     * TC: O(L * m * n), L = strs.length
     * SC: O(L * m * n)
     */
    private String[] strs;
    private int[][][] memo;

    public int findMaxForm(String[] strs, int m, int n) {
        this.strs = strs;
        this.memo = new int[strs.length][m + 1][n + 1];

        for (int i = 0; i < strs.length; ++i) {
            for (int j = 0; j < m + 1; ++j) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dp(0, m, n);
    }

    private int dp(int i, int curZeros, int curOnes) {
        if (i >= strs.length || curOnes < 0 || curZeros < 0) return 0;
        if (memo[i][curZeros][curOnes] != -1) return memo[i][curZeros][curOnes];

        int[] counts = countFreq(strs[i]);

        int pick = 0;
        if (counts[0] <= curZeros && counts[1] <= curOnes) {
            pick = dp(i + 1, curZeros - counts[0], curOnes - counts[1]) + 1;
        }
        int notPick = dp(i + 1, curZeros, curOnes);

        return memo[i][curZeros][curOnes] = Math.max(pick, notPick);
    }

    private int[] countFreq(String s) {
        int[] res = new int[2];
        for (char c : s.toCharArray()) {
            res[c - '0']++;
        }
        return res;
    }

    /**
     * Bottom up Knapsack 3D DP array
     * ----------------------------------------------
     * TC: O(L * m * n), L = strs.length
     * SC: O(m * n)
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

        for (int i = 1; i <= strs.length; ++i) {
            int[] counts = countFreq(strs[i - 1]);

            for (int curZeros = 0; curZeros <= m; ++curZeros) {
                for (int curOnes = 0; curOnes <= n; ++curOnes) {
                    if (counts[0] <= curZeros && counts[1] <= curOnes) {
                        dp[i][curZeros][curOnes] = Math.max(
                                dp[i - 1][curZeros][curOnes], // not pick
                                dp[i - 1][curZeros - counts[0]][curOnes - counts[1]] + 1 // pick
                        );
                    } else {
                        dp[i][curZeros][curOnes] = dp[i - 1][curZeros][curOnes];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    /**
     * Bottom up Knapsack DP, 2D DP array
     * ----------------------------------------------
     * TC: O(L * m * n), L = strs.length
     * SC: O(m * n)
     */
    public int findMaxForm3(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int[] counts = countFreq(str);

            for (int i = m; i >= counts[0]; --i) {
                for (int j = n; j >= counts[1]; --j) {
                    dp[i][j] = Math.max(
                            dp[i][j], // not pick
                            dp[i - counts[0]][j - counts[1]] + 1 // pick
                    );
                }
            }
        }
        return dp[m][n];
    }
}
