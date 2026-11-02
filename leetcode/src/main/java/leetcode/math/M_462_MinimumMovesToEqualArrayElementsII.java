package leetcode.math;

import java.util.Arrays;

public class M_462_MinimumMovesToEqualArrayElementsII {

    /**
     * Idea: for any k between a pair (a, b) with a <= k <= b
     * The cost of moving = (k - a) + (b - k) = b - a
     * Which doesn't depend on k as long as k stays inside every single pair's range
     * -> Sort the array, pair (0, n - 1); (1, n - 2); ...
     * -> Choose k as median number ~ nums[n / 2]
     * ---
     * TC: O(nlogn)
     * SC: O(1)
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int target = nums[nums.length / 2];
        for (int num : nums) {
            res += Math.abs(num - target);
        }
        return res;
    }
}
