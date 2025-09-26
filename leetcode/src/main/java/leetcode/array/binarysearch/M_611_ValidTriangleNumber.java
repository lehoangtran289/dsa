package leetcode.array.binarysearch;

import java.util.Arrays;

public class M_611_ValidTriangleNumber {
    public static void main(String[] args) {
        System.out.println(triangleNumber(new int[]{2, 2, 3, 4}));
    }

    /**
     * Binary Search
     * Idea: Sort + fix 2 sides, search for the 3rd side
     * -----------------------
     * TC: O(N^2 logN)
     * SC: O(1)
     */
    public static int triangleNumber(int[] nums) {
        int n = nums.length;
        int res = 0;

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int minValidIndex = search(nums, nums[i] + nums[j], j);

                if (minValidIndex != -1)
                    res += minValidIndex - j;
            }
        }

        return res;
    }

    // find largest index that nums[i] < target
    private static int search(int[] nums, int target, int l) {
        int res = -1;
        int r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] < target) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }
}
