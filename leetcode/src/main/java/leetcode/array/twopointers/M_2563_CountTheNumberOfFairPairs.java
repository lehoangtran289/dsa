package leetcode.array.twopointers;

import java.util.Arrays;

public class M_2563_CountTheNumberOfFairPairs {

    public static void main(String[] args) {
        System.out.println(countFairPairs(new int[]{0, 1, 7, 4, 4, 5}, 3, 6));
    }

    /**
     * 0, 1, 4, 4, 5, 7
     * 3 <= a + b <= 6
     */
    public static long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);

        // upper + 1 since we need sum <= upper
        return countLessThan(nums, upper + 1) - countLessThan(nums, lower);
    }

    /**
     * 2 pointers, find total pairs that have sum < upper
     */
    private static long countLessThan(int[] nums, int target) {
        long res = 0;
        int l = 0, h = nums.length - 1;

        while (l <= h) {
            if (nums[l] + nums[h] < target) {
                res += h - l;
                l++;
            } else {
                h--;
            }
        }
        return res;
    }
}
