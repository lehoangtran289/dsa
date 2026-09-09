package leetcode.dp;

import java.util.Arrays;

/**
 * Move right + down -> Possibly DP
 */
public class M_3742_MaximumPathScoreInAGrid {

    /**
     * DP 3D
     * Idea: dp[i][j][c] = max score to reach (i, j) with cost c
     * ---
     * TC: O(rows * cols * k)
     * SC: O(rows * cols * k)
     */
    public int maxPathScore(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;

        int[][][] dp = new int[rows][cols][k + 1];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (i == 0 && j == 0) continue; // skip starting cell

                int cellCost = grid[i][j] == 0 ? 0 : 1;

                for (int c = cellCost; c <= k; ++c) {
                    // only consider valid previous states
                    int bestScore = -1;

                    if (i > 0 && dp[i - 1][j][c - cellCost] >= 0) {
                        bestScore = dp[i - 1][j][c - cellCost] + grid[i][j];
                    }

                    if (j > 0 && dp[i][j - 1][c - cellCost] >= 0) {
                        bestScore = Math.max(bestScore, dp[i][j - 1][c - cellCost] + grid[i][j]);
                    }

                    dp[i][j][c] = bestScore;
                }
            }
        }

        int res = -1;
        for (int i = 0; i <= k; ++i) {
            res = Math.max(res, dp[rows - 1][cols - 1][i]);
        }
        return res;
    }
}
