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
        for (int num : nums) {
            if (num == 0) {
                zerosCount++;
                continue;
            }
            prodWithoutZero *= num;
        }

        int[] answer = new int[nums.length];
        for (int i = 0; i < answer.length; ++i) {
            if (nums[i] == 0) {
                answer[i] = zerosCount > 1 ? 0 : prodWithoutZero;
            } else {
                answer[i] = zerosCount > 0 ? 0 : prodWithoutZero / nums[i];
            }
        }
        return answer;
    }
}
