package com.leetcode.array;


public class _M_162_FindPeakElement {
    public static void main(String[] args) {
        _M_162_FindPeakElement obj = new _M_162_FindPeakElement();
        System.out.println(obj.findPeakElement(new int[]{1, 2, 3, 1})); // 2
    }

    public int findPeakElement(int[] nums) {
        // base case
        if (nums.length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;

        int lo = 0, hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = hi - (hi - lo) / 2;

            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] < nums[mid + 1]) { // mid < mid + 1 -> search right space
                lo = mid;
            } else if (nums[mid] < nums[mid - 1]) { // mid < mid - 1 -> search left space
                hi = mid;
            }
        }
        return lo;
    }
}
