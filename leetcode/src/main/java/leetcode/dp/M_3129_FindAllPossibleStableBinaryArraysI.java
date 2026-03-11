package leetcode.dp;

public class M_3129_FindAllPossibleStableBinaryArraysI {

    private static final int MOD = (int) 1e9 + 7;

    /**
     * DP 3D:
     * -------------
     * dp[i][j][0/1] = # stable arrays using i zeros & j ones, ending with 0 or 1
     * -------------
     * Base cases:
     * - dp[i][0][0] = 1 for all i <= limit (only one way to arrange i zeros)
     * - dp[0][j][1] = 1 for all j <= limit (only one way to arrange j ones)
     * <p>
     * Transition:
     * - To end with a 0, we can append a 0 to any array that ends with either a 0 or a 1, but we must ensure we don't exceed the limit of consecutive zeros.
     * Thus, dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1] - (i > limit ? dp[i-limit-1][j][1] : 0)
     * - Similar with 1
     * Finally, the answer will be the sum of dp[zero][one][0] and dp[zero][one][1], modulo MOD.
     */
    public int numberOfStableArrays(int zero, int one, int limit) {
        // dp[i][j][0/1] = # stable arrays using i zeros & j ones, ending with 0 or 1
        long[][][] dp = new long[zero + 1][one + 1][2];

        for (int i = 0; i <= Math.min(zero, limit); ++i) {
            dp[i][0][0] = 1; // only 1 valid array with i consecutive zeros and 0 ones
        }

        for (int j = 0; j <= Math.min(one, limit); ++j) {
            dp[0][j][1] = 1; // only 1 valid array with j consecutive ones and 0 zeros
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                dp[i][j][0] = mod(
                        dp[i - 1][j][0] // append 0 to arrays ending with 0
                        + dp[i - 1][j][1] // append 0 to arrays ending with 1
                        - (i > limit ? dp[i - limit - 1][j][1] : 0) // remove arrays that would exceed the limit of consecutive zeros
                );

                dp[i][j][1] = mod(
                        dp[i][j - 1][0] // append 1 to arrays ending with 0
                        + dp[i][j - 1][1] // append 1 to arrays ending with 1
                        - (j > limit ? dp[i][j - limit - 1][0] : 0) // remove arrays that would exceed the limit of consecutive ones
                );
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }

    private long mod(long val) {
        return ((val % MOD) + MOD) % MOD;
    }
}
