package leetcode.array;

import java.util.Arrays;

public class M_2779_MaximumBeautyOfAnArrayAfterApplyingOperation {
    public static void main(String[] args) {
        System.out.println(maximumBeauty(new int[]{4, 6, 1, 2}, 2));
    }

    public static int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        int r = 0;
        for (int l = 0; l < n; ++l) {
            // Expand the right pointer while the range condition is met
            while (r < n && nums[r] - nums[l] <= 2 * k) ++r;

            // We do not add 1 here as right is already pointing to one position beyond the valid range.
            res = Math.max(res, r - l);
        }

        return res;
    }
}
