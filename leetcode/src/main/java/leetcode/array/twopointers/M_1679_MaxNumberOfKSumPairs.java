package leetcode.array.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_1679_MaxNumberOfKSumPairs {
    static void main() {
        System.out.println(maxOperations(new int[]{1, 2, 3, 4}, 5)); //2
    }

    /**
     * HashMap + 1 pass
     * --------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxOperations(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> numCountMap = new HashMap<>();

        for (int num : nums) {
            int complement = k - num;

            if (numCountMap.getOrDefault(complement, 0) > 0) {
                res++;
                numCountMap.put(complement, numCountMap.get(complement) - 1);
            } else {
                numCountMap.put(num, numCountMap.getOrDefault(num, 0) + 1);
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
    public static int maxOperations2(int[] nums, int k) {
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
