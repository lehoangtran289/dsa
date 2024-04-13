package com.leetcode.array;

public class _M_238_ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        _M_238_ProductOfArrayExceptSelf obj = new _M_238_ProductOfArrayExceptSelf();
        int[] result = obj.productExceptSelf(new int[]{1, 2, 3, 4});
        for (int x : result) {
            System.out.print(x + " ");
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int prodWithoutZero = 1;
        int zerosCount = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                zerosCount++;
                continue;
            }
            prodWithoutZero *= nums[i];
        }

        int[] answer = new int[nums.length];
        for (int i = 0; i < answer.length; ++i) {
            if (nums[i] == 0) {
                if (zerosCount > 1) {
                    answer[i] = 0;
                } else {
                    answer[i] = prodWithoutZero;
                }
            } else {
                if (zerosCount > 0) {
                    answer[i] = 0;
                } else {
                    answer[i] = prodWithoutZero / nums[i];
                }
            }
        }
        return answer;
    }
}
