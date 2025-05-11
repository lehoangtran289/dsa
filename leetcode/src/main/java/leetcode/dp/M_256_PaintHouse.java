package leetcode.dp;

public class M_256_PaintHouse {
    public static void main(String[] args) {
        System.out.println(new M_256_PaintHouse().minCost2(new int[][]{
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19}
        })); // 10
    }

    /**
     * DP Tabulation
     * state: dp[i][j], min cost paint house i, cur color is j
     * start at n - 1
     * dp[i][0] = costs[i][0] + min(dp[i + 1][1], dp[i + 1][2])
     * -----------------------
     * TC: O(n)
     * SC: O(n)
     */
    public int minCost2(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n + 1][3];

        for (int i = n - 1; i >= 0; --i) {
            dp[i][0] = costs[i][0] + Math.min(dp[i + 1][1], dp[i + 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i + 1][0], dp[i + 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i + 1][0], dp[i + 1][1]);
        }

        return Math.min(dp[0][0], Math.min(dp[0][1], dp[0][2]));
    }

    /**
     * DP Memoization
     * state: dp[i][j], min cost paint house i, cur color is j
     * start at 0
     * -----------------------
     * TC: O(n)
     * SC: O(n)
     */
    private int[][] costs;
    private int[][] memo;
    private int n;

    public int minCost(int[][] costs) {
        this.n = costs.length;
        this.costs = costs;
        this.memo = new int[n][3];

        return Math.min(dp(0, 0), Math.min(dp(0, 1), dp(0, 2)));
    }

    private int dp(
            int pos,
            int color
    ) {
        if (memo[pos][color] != 0) return memo[pos][color];

        int cost = costs[pos][color];

        if (pos != n - 1) {
            if (color == 0) cost += Math.min(dp(pos + 1, 1), dp(pos + 1, 2));
            if (color == 1) cost += Math.min(dp(pos + 1, 0), dp(pos + 1, 2));
            if (color == 2) cost += Math.min(dp(pos + 1, 0), dp(pos + 1, 1));
        }

        return memo[pos][color] = cost;
    }
}
