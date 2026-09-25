package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k,
 * return the maximum length of a subarray that sums to k.
 * If there is not one, return 0 instead.
 */
public class M_325P_MaximumSizeSubarraySumEqualsK {
    static void main() {
        System.out.println(maxSubArrayLen(new int[]{-2, 1, -3, 4, -1, 2}, 3)); // 5
    }

    /**
     * Prefix Sum + HashMap
     * ----------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> sumIndexMap = new HashMap<>();

        int res = 0;
        int prefixSum = 0;

        for (int i = 0; i < nums.length; ++i) {
            prefixSum += nums[i];

            if (prefixSum == k) {
                res = i + 1;
            }

            int complement = prefixSum - k;
            if (sumIndexMap.containsKey(complement)) {
                res = Math.max(res, i - sumIndexMap.get(complement));
            }

            sumIndexMap.putIfAbsent(prefixSum, i);
        }

        return res;
    }
}
