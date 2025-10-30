package leetcode.dp;

import java.util.Arrays;

public class M_2464_MinimumSubarraysInAValidSplit {
    /**
     * Top Down DP
     * Idea:
     * - memo[i]: minimum splits for subarray starting at index i
     * - For each starting index, try all possible ending indices to form a valid subarray
     * ---------------------------
     * TC: O(n^2)
     * SC: O(n)
     */
    private final int MAX = 1 << 30;
    private int n;
    private int[] nums;
    private int[] memo; // start of array -> min splits

    public static void main(String[] args) {
        M_2464_MinimumSubarraysInAValidSplit solution = new M_2464_MinimumSubarraysInAValidSplit();
        System.out.println(solution.validSubarraySplit(new int[]{2, 6, 3, 4, 3})); // 2
        System.out.println(solution.validSubarraySplit(new int[]{3, 6, 5, 6})); // 1
    }

    public int validSubarraySplit(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.memo = new int[n];
        Arrays.fill(memo, -1);

        int res = dp(0);
        return res >= MAX ? -1 : res;
    }

    private int dp(int start) {
        if (start >= n) return 0;
        if (memo[start] != -1) return memo[start];

        int res = MAX;

        for (int i = start; i < n; ++i) {
            if (gcd(nums[start], nums[i]) != 1) {
                res = Math.min(res, 1 + dp(i + 1));
            }
        }

        return memo[start] = res;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
