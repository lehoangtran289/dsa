package leetcode.array.twopointers;

import java.util.*;

public class M_1679_MaxNumberOfKSumPairs {

    /**
     * HashMap + 2 passes
     * --------------------
     * TC: O(n)
     * SC: O(n)
     */
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int res = 0;
        for (int num : nums) {
            int complement = k - num;

            if (freq.getOrDefault(num, 0) > 0 && freq.getOrDefault(complement, 0) > 0) {
                if (num == complement && freq.get(num) <= 1) continue;

                res++;
                freq.put(num, freq.get(num) - 1);
                freq.put(complement, freq.get(complement) - 1);
            }
        }

        return res;
    }

    /**
     * Sorting + Two Pointers
     * -----------------------
     * TC: O(n log n) for sorting + O(n) for two pointers
     * SC: O(1)
     */
    public int maxOperations2(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        int l = 0, r = n - 1;

        Arrays.sort(nums);

        while (l < r) {
            int sum = nums[l] + nums[r];

            if (sum == k) {
                res++;
                l++;
                r--;
            } else if (sum > k) {
                r--;
            } else {
                l++;
            }
        }

        return res;
    }
}
