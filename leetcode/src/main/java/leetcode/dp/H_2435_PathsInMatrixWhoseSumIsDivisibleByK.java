package leetcode.dp;

public class H_2435_PathsInMatrixWhoseSumIsDivisibleByK {
    public static void main(String[] args) {
        System.out.println(numberOfPaths(
                new int[][]{
                        {5, 2, 4},
                        {3, 0, 5},
                        {0, 7, 2}
                },
                3
        )); // 2
    }

    /**
     * counting problem using DP, (hint for dp: matrix counting, down + right traverse)
     * --------------------
     * Observation:
     * dp[i][j] = dp[i - 1][j] if j == 0         ~ go down
     * dp[i][j] = dp[i][j - 1] if i == 0         ~ go right
     * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]    ~ any cell in between
     * --------------------
     * TC: O(m * n * k)
     * SC: O(m * n * k)
     */
    public static int numberOfPaths(int[][] grid, int k) {
        final int MOD = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;

        long[][][] dp = new long[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) continue;

                for (int r = 0; r < k; ++r) {
                    int prevMod = (r + k - grid[i][j] % k) % k;

                    if (i == 0) {
                        dp[i][j][r] = dp[i][j - 1][prevMod];
                    } else if (j == 0) {
                        dp[i][j][r] = dp[i - 1][j][prevMod];
                    } else {
                        dp[i][j][r] = (dp[i][j - 1][prevMod] + dp[i - 1][j][prevMod]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1][0];
    }
}
