package leetcode.array.twopointers;

import java.util.Arrays;

public class M_1099_TwoSumLessThanK {
    public static void main(String[] args) {
        System.out.println(twoSumLessThanK1(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60)); // 58
        System.out.println(twoSumLessThanK1(new int[]{10, 20, 30}, 15)); // -1
    }

    /**
     * 2 Pointers
     * Idea: Sort the array, use two pointers to find the largest sum less than k
     * -----------------
     * TC: O(n log n + n) = O(n log n)
     * SC: from O(logn) to O(n), depending on the implementation of the sorting algorithm.
     */
    public static int twoSumLessThanK1(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int res = -1;

        Arrays.sort(nums);

        while (l < r) {
            int sum = nums[l] + nums[r];

            if (sum < k) {
                res = Math.max(res, sum);
                l++;
            } else {
                r--;
            }
        }

        return res;
    }

    /**
     * Binary Search
     * Idea: Sort the array, for each element, use binary search to find the largest element
     * which when added to the current element is less than k
     * -----------------
     * TC: O(n log n + n log n) = O(n log n)
     * SC: from O(logn) to O(n), depending on the implementation of the sorting algorithm.
     */
    public static int twoSumLessThanK(int[] nums, int k) {
        int n = nums.length;
        int res = -1;

        Arrays.sort(nums);

        for (int i = 0; i < n - 1; ++i) {
            int complement = k - nums[i];
            int l = i + 1, r = n - 1;

            while (l <= r) {
                int mid = l + (r - l) / 2;

                if (nums[mid] < complement) {
                    res = Math.max(res, nums[mid] + nums[i]);
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return res;
    }
}
