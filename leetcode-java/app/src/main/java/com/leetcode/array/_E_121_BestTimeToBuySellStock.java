package com.leetcode.array;

public class _E_121_BestTimeToBuySellStock {
    public int maxProfit(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < nums.length; ++i) {
            min = Math.min(min, nums[i]); // min price so far
            max = Math.max(max, nums[i] - min); // max prof so far
        }
        return max;
    }

    public static void main(String[] args) {
        _E_121_BestTimeToBuySellStock obj = new _E_121_BestTimeToBuySellStock();
        System.out.println(obj.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
