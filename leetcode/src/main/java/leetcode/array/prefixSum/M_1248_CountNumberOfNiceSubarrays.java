package leetcode.array.prefixSum;

import java.util.*;

public class M_1248_CountNumberOfNiceSubarrays {

    /**
     * Problem: Count the number of subarrays with exactly k odd numbers
     * Prefix sum + Hashing
     * -----------------------
     * TC: O(n)
     * SC: O(n)
     */
    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int curSum = 0;
        int res = 0;

        for (int num : nums) {
            curSum += num % 2 == 1 ? 1 : 0;

            if (curSum == k) res++;

            res += freq.getOrDefault(curSum - k, 0);
            freq.put(curSum, freq.getOrDefault(curSum, 0) + 1);
        }

        return res;
    }
}
