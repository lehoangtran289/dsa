package leetcode.dp;

public class H_600_NonNegativeIntegersWithoutConsecutiveOnes {
    /**
     * Digit DP, top down approach
     * ----------------
     * State:
     * pos: current bit position we are processing
     * isTight: whether we are bounded by the prefix of n
     * prevBit: previous bit value (0/1)
     * ----------------
     * TC: O(log n) ~ where n is range number
     * SC: O(log n)
     */
    private int n;

    public static void main(String[] args) {
        H_600_NonNegativeIntegersWithoutConsecutiveOnes solution =
                new H_600_NonNegativeIntegersWithoutConsecutiveOnes();
        System.out.println(solution.findIntegers(5)); // 5
        System.out.println(solution.findIntegers(1)); // 2
        System.out.println(solution.findIntegers(2)); // 3
    }
    private int len;
    private Integer[][][] dp; // pos, isTight(0/1), prevBit(0/1) -> count

    public int findIntegers(int n) {
        this.n = n;
        this.len = Integer.SIZE - Integer.numberOfLeadingZeros(n);
        this.dp = new Integer[len][2][2];

        return dp(0, 1, 0); // start from pos = 0 ~ MSB
    }

    private int dp(int pos, int isTight, int prevBit) {
        if (pos == len) return 1;
        if (dp[pos][isTight][prevBit] != null) return dp[pos][isTight][prevBit];

        // get limit range if tight
        // limit range = bit value at pos in n
        int bitValue = n >> (len - 1 - pos) & 1;
        int limit = (isTight == 1) ? bitValue : 1;

        int count = 0;
        for (int i = 0; i <= limit; ++i) {
            if (prevBit == 1 && i == 1) continue; // constraint

            // next is tight iff current is tight and at limit value
            int nextTight = (isTight == 1 && i == limit) ? 1 : 0;
            count += dp(pos + 1, nextTight, i);
        }

        return dp[pos][isTight][prevBit] = count;
    }
}
