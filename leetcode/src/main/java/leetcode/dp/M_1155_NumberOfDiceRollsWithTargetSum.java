package leetcode.dp;

public class M_1155_NumberOfDiceRollsWithTargetSum {
    public static void main(String[] args) {
        System.out.println(numRollsToTarget(1, 6, 3)); // 1
        System.out.println(numRollsToTarget(2, 6, 7)); // 6
        System.out.println(numRollsToTarget(2, 5, 10)); // 1
    }


    /**
     * dp[i][j] = // number of ways to roll i dices, get target j
     * dp[i][j] += dp[i - 1][j - k]
     */
    public static int numRollsToTarget(int n, int k, int target) {
        final int MOD = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][target + 1];

        for (int i = 1; i <= k; ++i) {
            if (i <= target) dp[1][i] = 1;
        }

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= target; ++j) {
                for (int dice = 1; dice <= k; ++dice) {
                    if (j >= dice) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - dice]) % MOD;
                    }
                }
            }
        }

        return dp[n][target];
    }
}
