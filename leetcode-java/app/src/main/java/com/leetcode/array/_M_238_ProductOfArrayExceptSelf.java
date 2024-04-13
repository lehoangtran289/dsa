package com.leetcode.array;

import java.util.Arrays;

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

    // calculate prefix and suffix product
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] suff = new int[n];
        pre[0] = 1;
        suff[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] * nums[i + 1];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = pre[i] * suff[i];
        }
        return ans;
    }

    // calculate prefix and suffix product in O(1) space
    public int[] productExceptSelf3(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int curr = 1;
        for(int i = 0; i < n; i++) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        curr = 1;
        for(int i = n - 1; i >= 0; i--) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        return ans;
    }
}
