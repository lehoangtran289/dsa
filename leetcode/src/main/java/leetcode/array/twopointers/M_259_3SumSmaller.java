package leetcode.array.twopointers;

import java.util.Arrays;

/**
 * Problem: Count the number of triplets in the array such that the sum of the triplet is less than the target.
 */
public class M_259_3SumSmaller {

    /**
     * 2 Pointers
     * Idea: Sort the array, fix one element and
     * use two pointers to find pairs with sum less than (target - fixed element)
     * -----------------
     * TC: O(n^2)
     * SC: O(1)
     */
    public int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        int res = 0;

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; ++i) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum < target) {
                    res += r - l; // fix l, move r -> l => r - l pairs
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }
}
