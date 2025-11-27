package leetcode.array.prefixSum;

import java.util.Arrays;

public class M_3381_MaximumSubarraySumWithLengthDivisibleByK {

    /**
     * Prefix Sum + Modulo k
     * --------------------------
     * TC: O(n)
     * SC: O(k)
     * --------------------------
     */
    public long maxSubarraySum(int[] nums, int k) {
        final long MAX = 1L << 62;
        int n = nums.length;

        long[] minSoFar = new long[k];
        Arrays.fill(minSoFar, MAX);
        minSoFar[0] = 0L; // when subarray starts at 0

        long res = -MAX;
        long curSum = 0;

        // pre-fill first k-1 elements
        for (int i = 0; i < k - 1; ++i) {
            curSum += nums[i];
            minSoFar[(i + 1) % k] = curSum;
        }

        // process from k-1 to end (since subarray length must be at least k)
        for (int i = k - 1; i < n; ++i) {
            curSum += nums[i];
            int rem = (i + 1) % k;

            res = Math.max(res, curSum - minSoFar[rem]);
            minSoFar[rem] = Math.min(minSoFar[rem], curSum);
        }

        return res;
    }
}
