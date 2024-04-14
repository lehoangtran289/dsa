package com.leetcode.array;

public class _M_33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        _M_33_SearchInRotatedSortedArray obj = new _M_33_SearchInRotatedSortedArray();
        System.out.println(obj.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(obj.search(new int[]{3, 1}, 0));
    }

    public int search(int[] nums, int target) {
        if (nums == null) return -1;

        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            System.out.println(lo + " " + mid + " " + hi);

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[lo] <= nums[mid]) {
                if (target >= nums[lo] && target < nums[mid]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            if (nums[mid] <= nums[hi]) {
                if (target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
        }
        return nums[lo] == target ? lo : -1;
    }
}
