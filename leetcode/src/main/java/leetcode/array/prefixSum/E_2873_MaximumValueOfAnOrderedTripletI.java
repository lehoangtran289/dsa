package leetcode.array.prefixSum;

public class E_2873_MaximumValueOfAnOrderedTripletI {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        int curMaxLeft = 0;
        for (int i = 0; i < n; ++i) {
            maxLeft[i] = Math.max(curMaxLeft, nums[i]);
            curMaxLeft = maxLeft[i];
        }

        int curMaxRight = 0;
        for (int i = n - 1; i >= 0; --i) {
            maxRight[i] = Math.max(curMaxRight, nums[i]);
            curMaxRight = maxRight[i];
        }

        long res = 0;
        for (int j = 1; j < n - 1; ++j) {
            res = Math.max(res, (long) (maxLeft[j - 1] - nums[j]) * maxRight[j + 1]);
        }
        return res;
    }
}
