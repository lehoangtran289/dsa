package leetcode.array.twopointers;

import java.util.Arrays;

public class M_16_3SumClosest {
    public static void main(String[] args) {
        System.out.println(new M_16_3SumClosest().threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2
    }

    /**
     * Find the sum of three integers in an array that is closest to a target value.
     * Similar approach to 3 sum
     * -----------------
     * TC: O(n^2)
     * SC: O(1)
     */
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int diff = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for (int i = 0; i < n; ++i) {
            int l = i + 1, r = n - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }

                if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return target - diff;
    }
}
