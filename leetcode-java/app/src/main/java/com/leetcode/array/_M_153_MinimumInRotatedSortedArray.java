package com.leetcode.array;

public class _M_153_MinimumInRotatedSortedArray {
    public static void main(String[] args) {
        _M_153_MinimumInRotatedSortedArray obj = new _M_153_MinimumInRotatedSortedArray();
        System.out.println(obj.findMin(new int[]{3, 4, 5, 1, 2}));
    }

    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) { // return when only 1 element
            int mid = (hi + lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1; // skip mid since it is not answer
            } else {
                hi = mid;
            }
        }
        return nums[lo]; // return the only element left
    }
}
