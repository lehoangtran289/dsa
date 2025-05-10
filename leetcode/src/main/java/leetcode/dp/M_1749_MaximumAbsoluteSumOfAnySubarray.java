package leetcode.dp;

public class M_1749_MaximumAbsoluteSumOfAnySubarray {
    public static void main(String[] args) {
        System.out.println(maxAbsoluteSum(new int[]{-7, -1, 0, -2, 1, 3, 8, -2, -6, -1, -10, -6, -6, 8, -4, -9, -4, 1, 4, -9})); // 44
        System.out.println(maxAbsoluteSum(new int[]{1, -3, 2, 3, -4})); // 5
        System.out.println(maxAbsoluteSum(new int[]{2, -5, 1, -4, 3, -2})); // 8
    }

    /**
     * Kadane approach
     * -----------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxAbsoluteSum(int[] nums) {
        int curMin = 0, minSum = 0;
        int curMax = 0, maxSum = 0;

        for (int num : nums) {
            curMin = Math.min(curMin + num, num);
            minSum = Math.min(minSum, curMin);

            curMax = Math.max(curMax + num, num);
            maxSum = Math.max(maxSum, curMax);
        }

        return Math.max(Math.abs(maxSum), Math.abs(minSum));
    }
}
