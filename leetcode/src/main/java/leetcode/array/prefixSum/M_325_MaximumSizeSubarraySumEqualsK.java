package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k,
 * return the maximum length of a subarray that sums to k.
 * If there is not one, return 0 instead.
 */
public class M_325_MaximumSizeSubarraySumEqualsK {

    /**
     * Prefix Sum + HashMap
     * ----------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> seen = new HashMap<>();
        int n = nums.length;
        int curSum = 0;
        int res = 0;

        for (int i = 0; i < n; ++i) {
            curSum += nums[i];
            if (curSum == k) res = i + 1;

            int complement = curSum - k;
            if (seen.containsKey(complement)) {
                res = Math.max(res, i - seen.get(complement));
            }

            seen.putIfAbsent(curSum, i);
        }

        return res;
    }
}
