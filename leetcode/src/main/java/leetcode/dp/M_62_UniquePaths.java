package leetcode.dp;

import java.util.Arrays;

public class M_62_UniquePaths {

    /**
     * Idea: In DP 2d, we only need row i-th and (i-1)-th -> Can convert to 1d
     * ---
     * TC: O(rows * cols)
     * SC: O(cols)
     */
    public int uniquePaths(int rows, int cols) {
        int[] dp = new int[cols];
        Arrays.fill(dp, 1);

        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < cols; ++j) {
                dp[j] = dp[j] + dp[j - 1]; // UP + LEFT
            }
        }

        return dp[cols - 1];
    }

    /**
     * TC: O(rows * cols)
     * SC: O(rows * cols)
     */
    public int uniquePaths2d(int rows, int cols) {
        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < cols; ++j) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < cols; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[rows - 1][cols - 1];
    }
}
