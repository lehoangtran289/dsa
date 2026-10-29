package leetcode.dp;

public class M_3882_MinimumXORPathInAGrid {

    /**
     * Idea:
     * - Try to store all achievable XOR values at each cells, using bit mask (since values < 1024)
     * - Base case: dp[0][0][grid[0][0]] = true
     * - Note: a ^ b = c => a = c ^ b
     * - DP: dp[i][j][value] = true if either:
     *      + up cell: dp[i - 1][j][value ^ grid[i][j]] = true
     *      + left cell: dp[i][j - 1][value ^ grid[i][j]] = true
     * ---
     * TC: O(rows * cols * 1024)
     * SC: O(rows * cols * 1024)
     */
    public int minCost(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][][] dp = new boolean[rows][cols][1024];

        // init base boundary cases
        dp[0][0][grid[0][0]] = true;

        // dp
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                for (int v = 0; v < 1024; ++v) {
                    if (
                            (i > 0 && dp[i - 1][j][v ^ grid[i][j]])
                            || (j > 0 && dp[i][j - 1][v ^ grid[i][j]])
                    ) {
                        dp[i][j][v] = true;
                    }
                }
            }
        }

        // construct result from final cell
        for (int v = 0; v < 1024; ++v) {
            if (dp[rows - 1][cols - 1][v]) return v;
        }
        return -1;
    }
}
