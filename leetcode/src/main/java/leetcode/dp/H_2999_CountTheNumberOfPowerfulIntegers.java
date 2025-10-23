package leetcode.dp;

import java.util.Arrays;

/**
 * Digit DP
 */
public class H_2999_CountTheNumberOfPowerfulIntegers {
    public static void main(String[] args) {
        System.out.println(
                new H_2999_CountTheNumberOfPowerfulIntegers().numberOfPowerfulInt(
                        1,        // low = 0001
                        6000,           // high = 6000
                        4,              // limit = 4
                        "124"           // s = 124
                )
        ); // 5;
    }

    // input
    private int n;
    private String low;
    private String high;
    private int limit;
    private String s;
    // utils args
    private int prefixLen; // prefix length
    private long[] memo;

    /**
     * Digits DP approach
     * The idea is to use digits DP to count the number of integers with a certain prefix.
     * ---
     * We can use a recursive function that takes the current position in the number, whether we are still limited by the lower or upper bound, and the current prefix.
     * We can then iterate through all possible digits for the current position and check if they are valid according to the prefix and the bounds.
     * Finally, we can sum up all the valid counts.
     *
     * @param start  The lower bound (inclusive)
     * @param finish The upper bound (inclusive)
     * @param limit  The maximum digit allowed
     * @param s      The prefix string
     * @return The count of powerful integers in the range [start, finish]
     */
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        this.high = Long.toString(finish);
        this.n = high.length();
        this.low = Long.toString(start);
        this.low = addPadding(low, n);
        this.limit = limit;
        this.s = s;
        this.memo = new long[n];
        Arrays.fill(this.memo, -1);
        this.prefixLen = n - s.length(); // prefix length

        return dp(0, true, true);
    }

    private long dp(
            int i, // current index
            boolean limitLow, // whether we are still limited by the lower bound
            boolean limitHigh // whether we are still limited by the upper bound
    ) {
        if (i == n) return 1;
        if (memo[i] != -1 && !limitLow && !limitHigh) return memo[i];

        int lo = limitLow ? low.charAt(i) - '0' : 0;
        int hi = limitHigh ? high.charAt(i) - '0' : 9;
        int upper = Math.min(hi, limit);
        long res = 0;

        if (i < prefixLen) {
            for (int digit = lo; digit <= upper; ++digit) {
                res += dp(
                        i + 1,
                        limitLow && digit == lo,
                        limitHigh && digit == hi
                );
            }
        } else {
            int x = s.charAt(i - prefixLen) - '0';
            if (lo <= x && x <= upper) {
                res = dp(
                        i + 1,
                        limitLow && x == lo,
                        limitHigh && x == hi
                );
            }
        }

        if (!limitLow && !limitHigh) {
            memo[i] = res;
        }
        return res;
    }

    private String addPadding(String s, int n) {
        int len = s.length();
        StringBuilder sb = new StringBuilder(s);

        while (len < n) {
            sb.insert(0, '0');
            len++;
        }
        return sb.toString();
    }
}
