package leetcode.dp;

public class M_3201_FindTheMaximumLengthOfValidSubsequenceI {

    /**
     * DP bottom up
     * -----------------
     * 4 cases:
     * 1. all even -> count all 0
     * 2. all odd -> count all 1
     * 3. 1 0 1 0
     * 4. 0 1 0 1
     */
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int zeroCount = 0, oneCount = 0;

        for (int i = 0; i < n; ++i) {
            nums[i] %= 2;

            if (nums[i] == 0) zeroCount++;
            else oneCount++;
        }

        // max length of alternating subarray ending at index i-1 and ending with an even (0) or odd (1)
        int[][] dp = new int[n + 1][2];

        for (int i = 1; i <= n; ++i) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 1][1];

            if (nums[i - 1] == 0) {
                // If current is even, extend previous odd-ending sequence
                dp[i][0] = Math.max(
                        dp[i - 1][0],
                        dp[i - 1][1] + 1
                );
            } else {
                // If current is odd, extend previous even-ending sequence
                dp[i][1] = Math.max(
                        dp[i - 1][1],
                        dp[i - 1][0] + 1
                );
            }
        }

        return Math.max(
                zeroCount,                  // all even
                Math.max(
                        oneCount,           // all odd
                        Math.max(
                                dp[n][0],   // alternating ending with even
                                dp[n][1]    // alternating ending with odd
                        )
                )
        );
    }

    /**
     * No DP. Just check 4 patterns
     */
    public int maximumLength2(int[] nums) {
        int n = nums.length;

        // all even or all odd
        int zeroCount = 0, oneCount = 0;
        int alternateZero = 0;
        int alternateOne = 0;

        for (int num : nums) {
            if (num % 2 == 0) zeroCount++;
            else oneCount++;

            int nextZero = alternateZero % 2 == 0 ? 0 : 1;
            int nextOne = alternateOne % 2 == 0 ? 1 : 0;

            if (num % 2 == nextZero) {
                alternateZero++;
            }

            if (num % 2 == nextOne) {
                alternateOne++;
            }
        }

        return Math.max(
                zeroCount, Math.max(        // all odd
                        oneCount,               // all even
                        Math.max(
                                alternateZero,      // alternating 0
                                alternateOne        // alternating 1
                        )
                )
        );
    }
}
