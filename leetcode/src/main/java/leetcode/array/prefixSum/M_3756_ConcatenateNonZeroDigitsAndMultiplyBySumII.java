package leetcode.array.prefixSum;

public class M_3756_ConcatenateNonZeroDigitsAndMultiplyBySumII {

    /**
     * Prefix Sum
     * ---
     * TC: O(n + q) where n is the length of the string and q is the number of queries
     * SC: O(n)
     */
    public static int[] sumAndMultiply(String s, int[][] queries) {
        final int MOD = (int) 1e9 + 7;
        int n = s.length();

        long[] xVal = new long[n + 1];
        long[] digitSum = new long[n + 1];
        int[] digitCnt = new int[n + 1];
        long[] pow10 = new long[n + 1]; // Precompute powers of 10 % MOD
        pow10[0] = 1;

        // build prefix array (x values, digit sum, digit count)
        for (int i = 0; i < n; ++i) {
            int digit = s.charAt(i) - '0';

            if (digit == 0) {
                xVal[i + 1] = xVal[i];
                digitCnt[i + 1] = digitCnt[i];
            } else {
                xVal[i + 1] = (xVal[i] * 10 + digit) % MOD;
                digitCnt[i + 1] = digitCnt[i] + 1;
            }
            digitSum[i + 1] = digitSum[i] + digit;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        // process queries
        int[] res = new int[queries.length];
        int index = 0;

        for (int[] q : queries) {
            int l = q[0], r = q[1];

            int digits = digitCnt[r + 1] - digitCnt[l];

            // + MOD to ensure the result is non-negative before taking modulo MOD again.
            long x = (xVal[r + 1] - (xVal[l] * pow10[digits] % MOD) + MOD) % MOD;
            long sum = digitSum[r + 1] - digitSum[l];

            res[index++] = (int) (x * sum % MOD);
        }

        return res;
    }
}
