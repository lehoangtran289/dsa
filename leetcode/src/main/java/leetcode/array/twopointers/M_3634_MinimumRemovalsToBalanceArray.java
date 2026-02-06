package leetcode.array.twopointers;

import java.util.Arrays;

public class M_3634_MinimumRemovalsToBalanceArray {

    /**
     * Sorting + 2 pointers
     * Expand j while nums[j] <= k * nums[i] to maximize the balanced window; answer = n - (j - i + 1).
     * -----------------------------
     * TC: O(n lgn) for sorting + O(n) for two pointers
     * SC: O(1) if we can sort in place, otherwise O(n)
     */
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int res = 1 << 30;
        int r = 0;

        for (int l = 0; l < n; ++l) {
            while (r < n && (double) nums[r] / k <= nums[l]) {
                r++;
            }
            res = Math.min(res, n - (r - l));
        }

        return res;
    }
}
