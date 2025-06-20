package leetcode.array.binarysearch;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer p, return the minimum possible value of the maximum difference
 * between any two elements in a pair formed by selecting p pairs from nums.
 */
public class M_2616_MinimizeTheMaximumDifferenceOfPairs {
    public static void main(String[] args) {
        System.out.println(minimizeMax(new int[]{10, 1, 2, 7, 1, 3}, 2)); // 1
        System.out.println(minimizeMax(new int[]{4, 2, 1, 2}, 1)); // 0
        System.out.println(minimizeMax(new int[]{1, 1, 0, 3}, 2)); // 2
    }

    /**
     * Binary search on answer
     * Pattern: find (min out of all max) or (max of all min) => Usually greedy / binary search
     * ------------------
     * TC: O(nlogn) - sorting
     * SC: O(1)
     */
    public static int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);

        int n = nums.length;
        int res = 0;
        int l = 0, r = nums[n - 1] - nums[0]; // diff threshold

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (isValid(nums, mid, p)) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }

    private static boolean isValid(int[] nums, int threshold, int p) {
        int count = 0;
        int i = 1;

        while (i < nums.length) {
            // check all pairs if it <= threshold
            if (nums[i] - nums[i - 1] <= threshold) {
                count++;
                i += 2;
            } else {
                i++;
            }
        }

        return count >= p;
    }
}
