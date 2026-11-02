package leetcode.dp;

/**
 * Only DOWN + RIGHT dir, when encounter cell = 1 -> change dir, RIGHT -> DOWN, DOWN -> RIGHT
 * Calculates # unique paths to (rows - 1, cols - 1) cell
 */
public class M_3665_TwistedMirrorPathCount {

    /**
     * Idea: dp[i][j] = number of paths to cell (i, j) from either direction,
     * with 0 = come from DOWN dir, 1 = come from RIGHT dir
     * ---
     * Start from a cell -> calculate paths to next cell dp[i + 1][j][0] (down) and dp[i][j + 1][1] (right)
     * If grid[i][j] == 0
     *      dp[i + 1][j][0] = dp[i][j + 1][1] = totalPaths of dp[i][j]
     * If grid[i][j] == 1
     *      dp[i + 1][j][0] = dp[i][j][1] (must come from RIGHT dir)
     *      dp[i][j + 1][1] = dp[i][j][0] (must come from DOWN dir)
     * ---
     * TC: O(rows * cols)
     * SC: O(rows * cols)
     */
    public int uniquePaths(int[][] grid) {
        final int MOD = (int) 1e9 + 7;
        final int rows = grid.length, cols = grid[0].length;

        // dp[i][j] = number of paths to cell (i, j) from either direction
        // 0 = down, 1 = right
        long[][][] dp = new long[rows][cols][2];
        dp[0][0][0] = 1L;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 0) {
                    long totalPaths = (dp[i][j][0] + dp[i][j][1]) % MOD;
                    if (i + 1 < rows) dp[i + 1][j][0] = totalPaths; // next DOWN paths [0] = all paths from cur cell
                    if (j + 1 < cols) dp[i][j + 1][1] = totalPaths; // next RIGHT paths [1] = all paths from cur cell
                } else {
                    if (i + 1 < rows) dp[i + 1][j][0] = dp[i][j][1]; // next DOWN paths [0] = paths come from RIGHT [1]
                    if (j + 1 < cols) dp[i][j + 1][1] = dp[i][j][0]; // next RIGHT paths [1] = paths come from DOWN [0]
                }
            }
        }

        return (int) (dp[rows - 1][cols - 1][0] + dp[rows - 1][cols - 1][1]) % MOD;
    }
}
