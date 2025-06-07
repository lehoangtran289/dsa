package leetcode.array.binarysearch;

import java.util.Arrays;

public class M_34_FindFirstAndLastInSortedArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }

    /**
     * Binary Search
     * -----------------------
     * Idea: Find the first and last occurrence of the target using binary search
     * -----------------------
     * TC: O(log n)
     * SC: O(1)
     */
    public static int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        if (first == -1) return new int[] {-1, -1};

        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int res = -1;
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) {
                res = mid;
                if (isFirst) r = mid - 1;
                else l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }
}
