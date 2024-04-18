package com.leetcode.array;

public class _M_713_SubarrayProductLessThanK {
    public static void main(String[] args) {
        _M_713_SubarrayProductLessThanK obj = new _M_713_SubarrayProductLessThanK();
        System.out.println(obj.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
         if (k <= 1) return 0;

         int lo = 0, hi = 0, prod = 1;
         int count = 0;

         while (hi < nums.length) {
             prod *= nums[hi];
             while (prod >= k) {
                 prod /= nums[lo];
                 lo++;
             }
             count += (hi - lo) + 1; // 1 ~ single element subarray till hi
             hi++;
         }
         return count;
    }

    public int numSubarrayProductLessThanK_bruteforce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            int prod = 1;
            for (int j = i; j < nums.length; ++j) {
                prod *= nums[j];
                if (prod >= k) break;
                count++;
            }
        }
        return count;
    }
}
