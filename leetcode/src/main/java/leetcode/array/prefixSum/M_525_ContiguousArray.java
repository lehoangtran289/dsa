package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class M_525_ContiguousArray {

    /**
     * Prefix Sum + HashMap
     * -----------
     * Idea: Convert 0 to 1, and 1 to -1. Find the longest subarray with sum = 0.
     * Use a HashMap to store the first occurrence of each prefix sum.
     * If the same prefix sum appears again, it means the subarray between these two indices has a sum of 0.
     * -----------
     * TC: O(n)
     * SC: O(n)
     */
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> seenCount = new HashMap<>();
        int n = nums.length;
        int count = 0;
        int res = 0;

        for (int i = 0; i < n; ++i) {
            count += nums[i] == 0 ? 1 : -1;

            if (count == 0) res = Math.max(res, i + 1);

            if (seenCount.containsKey(count)) {
                res = Math.max(res, i - seenCount.get(count));
            } else {
                seenCount.put(count, i);
            }
        }

        return res;
    }
}
