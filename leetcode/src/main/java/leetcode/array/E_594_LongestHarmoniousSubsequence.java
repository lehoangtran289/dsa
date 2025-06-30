package leetcode.array;

import java.util.*;

public class E_594_LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        System.out.println(findLHS1(new int[]{1, 3, 2, 2, 5, 2, 3, 7})); // Output: 5
        System.out.println(findLHS1(new int[]{1, 2, 3, 4})); // Output: 2
    }

    /**
     * 1. Clarification:
     * - Range of nums.length ? [1, 2 * 10^4]
     * - Range of nums[i] ? [-10^9, 10^9]
     * ----
     * 2. Test cases:
     * nums = [1,3,2,2,5,2,3,7] => [3,2,2,2,3] (length 5)
     * nums = [1,2,3,4] => [1,2] or [2,3] (length 2)
     * ----
     * 3. Solution:
     * 3.1. Hashmap to count frequency of each number
     *      - TC: O(n); SC: O(n)
     * 3.2. Sorting + sliding window
     *      - TC: O(n log n); SC: O(1)
     * ----
     * 4. Dry run
     */
    public static int findLHS1(int[] nums) {
        int n = nums.length;
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (freq.containsKey(num + 1)) {
                res = Math.max(res, freq.get(num) + freq.get(num + 1));
            }
        }

        return res;
    }

    public static int findLHS2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        int l = 0;

        for (int r = 0; r < n; ++r) {
            while (l <= r && nums[r] - nums[l] > 1) {
                l++;
            }

            if (nums[r] - nums[l] == 1) {
                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }
}
