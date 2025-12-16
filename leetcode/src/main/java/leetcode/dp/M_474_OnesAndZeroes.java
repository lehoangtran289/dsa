package leetcode.dp;

import java.util.Arrays;

/**
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 */
public class M_474_OnesAndZeroes {

    /**
     * Bottom up Knapsack DP, 2D DP array
     * ----------------------------------------------
     * dp[i][j] = max subsets using at most "i" ones and "j" zeros
     * dp[i][j] = max(dp[i][j], dp[i - ones][j - zeros] + 1)
     * ----------------------------------------------
     * TC: O(L * m * n), L = strs.length
     * SC: O(m * n)
     */
    public int findMaxForm2D(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int[] counts = countOnesZeros(str);

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

    /**
     * Bottom up Knapsack DP, 3D DP array
     * ------------------------------
     * dp[i][j][k] = max subsets using first "i" elements, constraint with "j" ones and "k" zeros
     * dp[i][j][k] = max(dp[i - 1][j][k], dp[i - 1][j - ones[i]][k - zeros[i]])
     * ----------------------------------------------
     * TC: O(L * m * n), L = strs.length
     * SC: O(L * m * n)
     */
    public int findMaxForm3D(String[] strs, int m, int n) {
        int arrLength = strs.length;
        int[][][] dp = new int[arrLength + 1][m + 1][n + 1];

        for (int i = 1; i <= arrLength; ++i) {
            int index = i - 1;
            int[] count = countOnesZeros(strs[index]);

            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= n; ++k) {
                    if (count[0] <= j && count[1] <= k) {
                        dp[i][j][k] = Math.max(
                                dp[i - 1][j - count[0]][k - count[1]] + 1,
                                dp[i - 1][j][k]
                        );
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }

        return dp[arrLength][m][n];
    }

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

        int[] counts = countOnesZeros(strs[i]);

        int pick = 0;
        if (counts[0] <= curZeros && counts[1] <= curOnes) {
            pick = dp(i + 1, curZeros - counts[0], curOnes - counts[1]) + 1;
        }
        int notPick = dp(i + 1, curZeros, curOnes);

        return memo[i][curZeros][curOnes] = Math.max(pick, notPick);
    }

    private int[] countOnesZeros(String s) {
        int[] res = new int[2];
        for (char c : s.toCharArray()) {
            res[c - '0']++;
        }
        return res;
    }
}
