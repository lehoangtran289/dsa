package leetcode.dp;

public class M_221_MaximalSquare {
    public static void main(String[] args) {
        System.out.println(new M_221_MaximalSquare().maximalSquare(
                new char[][]{{'0', '1'}}
        ));
    }

    /**
     * ----------------------------------------------
     * Top-down DP
     * ----------------------------------------------
     */
    private int[][] memo;
    private char[][] matrix;

    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        this.matrix = matrix;
        this.memo = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                memo[i][j] = -1;
            }
        }

        int res = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                res = Math.max(res, dp(i, j));
            }
        }

        return res * res;
    }

    private int dp(
            int x,
            int y
    ) {
        if (x >= matrix.length || y >= matrix[0].length) return 0;
        if (matrix[x][y] == '0') return 0;
        if (memo[x][y] != -1) return memo[x][y];

        int right = dp(x, y + 1);
        int down = dp(x + 1, y);
        int diag = dp(x + 1, y + 1);

        memo[x][y] = Math.min(
                Math.min(right, down),
                diag
        ) + 1;
        return memo[x][y];
    }

    /**
     * ----------------------------------------------
     * Bottom-up DP
     * ----------------------------------------------
     */
    public int maximalSquare2(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n + 1][m + 1];
        int res = 0;

        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                if (matrix[i][j] == '0') continue;

                dp[i][j] = Math.min(
                        Math.min(dp[i + 1][j], dp[i][j + 1]),
                        dp[i + 1][j + 1]
                ) + 1;
                res = Math.max(res, dp[i][j]);
            }
        }

        return res * res;
    }
}
