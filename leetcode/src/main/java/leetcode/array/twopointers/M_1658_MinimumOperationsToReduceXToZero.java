package leetcode.array.twopointers;

import static leetcode.array.prefixSum.M_325P_MaximumSizeSubarraySumEqualsK.maxSubArrayLen;

public class M_1658_MinimumOperationsToReduceXToZero {
    static void main() {
        System.out.println(minOperations(new int[]{-2, 1, -3, 4, -1, 2}, 3)); // 4
    }

    /**
     * Idea: positive only -> sliding window to find max subarray sum = totalSum - x
     * ---
     * TC: O(n)
     * SC: O(1)
     */
    public static int minOperations(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        int complement = totalSum - x;
        if (complement == 0) return n;

        int res = -1, curSum = 0;

        int l = 0;
        for (int r = 0; r < n; ++r) {
            curSum += nums[r];

            while (l < r && curSum > complement) {
                curSum -= nums[l];
                l++;
            }

            if (curSum == complement) {
                res = Math.max(res, r - l + 1);
            }
        }

        return res == -1 ? -1 : n - res;
    }

    /**
     * Idea: Can be converted into max size subarray sum = k problem
     */
    public int minOperations1(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        int complement = totalSum - x;
        if (complement == 0) return n;

        int maxSubArrayLen = maxSubArrayLen(nums, complement);
        return maxSubArrayLen == -1 ? -1 : n - maxSubArrayLen;
    }
}
