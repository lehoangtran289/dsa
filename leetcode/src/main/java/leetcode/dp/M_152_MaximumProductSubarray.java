package leetcode.dp;

public class M_152_MaximumProductSubarray {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-3, 2, -4})); // 24
    }

    /**
     * Kadane approach
     * -----------------------
     * Idea: Since we observed that negative values can produce the maximum product,
     * we keep track of both maximum product and the minimum product
     * -----------------------
     * TC: O(n)
     * SC: O(n + n) = O(n)
     */
    public static int maxProduct(int[] nums) {
        int n = nums.length;

        int[] dpMax = new int[n];
        int[] dpMin = new int[n];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            dpMax[i] = Math.max(
                    nums[i],
                    Math.max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i])
            );

            dpMin[i] = Math.min(
                    nums[i],
                    Math.min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i])
            );
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            res = Math.max(dpMax[i], res);
        }

        return res;
    }
}
