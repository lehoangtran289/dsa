package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there is not one, return 0 instead.
 */
public class M_325_MaximumSizeSubarraySumEqualsK {

    /**
     * PREFIX SUM + HASHMAP
     */
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length;
        int curSum = 0;
        int res = 0;

        for (int i = 0; i < n; ++i) {
            curSum += nums[i];

            // Check if all of the numbers seen so far sum to k.
            if (curSum == k) res = i + 1;

            // If any subarray seen so far sums to k, then
            // update the length of the longest_subarray.
            if (freq.containsKey(curSum - k)) {
                res = Math.max(res, i - freq.get(curSum - k));
            }

            // Only add the current prefix_sum index pair to the
            // map if the prefix_sum is not already in the map.
            if (!freq.containsKey(curSum)) {
                freq.put(curSum, i);
            }
        }

        return res;
    }
}
