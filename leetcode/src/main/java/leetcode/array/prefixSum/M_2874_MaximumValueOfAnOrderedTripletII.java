package leetcode.array.prefixSum;

public class M_2874_MaximumValueOfAnOrderedTripletII {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        int maxLeftSoFar = 0;
        for (int i = 0; i < n; ++i) {
            maxLeftSoFar = Math.max(maxLeftSoFar, nums[i]);
            maxLeft[i] = maxLeftSoFar;
        }

        int maxRightSoFar = 0;
        for (int i = n - 1; i >= 0; --i) {
            maxRightSoFar = Math.max(maxRightSoFar, nums[i]);
            maxRight[i] = maxRightSoFar;
        }

        long res = 0;
        for (int j = 1; j < n - 1; ++j) {
            res = Math.max(res, (long) (maxLeft[j - 1] - nums[j]) * maxRight[j + 1]);
        }
        return res;
    }
}
