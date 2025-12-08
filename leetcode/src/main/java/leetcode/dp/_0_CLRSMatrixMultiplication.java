package leetcode.dp;

/**
 * CLRS Matrix Multiplication
 * ------------------------------
 * Given a sequence of matrices, find the most efficient way (number of multiplications)
 * to multiply these matrices together.
 * ------------------------------
 */
public class _0_CLRSMatrixMultiplication {
    public static void main(String[] args) {
        _0_CLRSMatrixMultiplication solver = new _0_CLRSMatrixMultiplication();

        // matrices: A1(30x35), A2(35x15), A3(15x5), A4(5x10), A5(10x20), A6(20x25)
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        System.out.println(solver.matrixChainOrder(p)); // Output: 15125, ((A1 x (A2 x A3)) x ((A4 x A5) x A6))

        p = new int[]{5, 10, 3, 12, 5, 50, 6};
        System.out.println(solver.matrixChainOrder(p)); // Output: 2010, ((A1 x A2) x ((A3 x A4) x (A5 x A6)))
    }

    /**
     * Bottom-up DP approach
     * ------------------------------
     * Recursion relation:
     * dp[i][i] = 0 for all i
     * dp[i][j] = min(dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]) for all i <= k < j
     * ------------------------------
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public int matrixChainOrder(int[] p) {
        int n = p.length - 1;
        int[][] dp = new int[n + 1][n + 1]; // dp[i][j] = min cost for multiplying Ai...Aj
        int[][] split = new int[n + 1][n + 1]; // split[i][j] = index k at which to split

        // dp is traversed by length of the chain to reuse previously computed results
        for (int len = 2; len <= n; ++len) {
            for (int i = 1; i <= n - len + 1; ++i) {
                int j = i + len - 1;

                dp[i][j] = 1 << 30;

                for (int k = i; k < j; ++k) {
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];

                    if (cost < dp[i][j]) {
                        split[i][j] = k;
                        dp[i][j] = cost;
                    }
                }
            }
        }
        System.out.println(printOptimalParens(split, 1, n));

        return dp[1][n];
    }

    private String printOptimalParens(int[][] split, int i, int j) {
        if (i == j) return "A" + i;

        return "(" + printOptimalParens(split, i, split[i][j]) +
               " x " + printOptimalParens(split, split[i][j] + 1, j) + ")";
    }
}
