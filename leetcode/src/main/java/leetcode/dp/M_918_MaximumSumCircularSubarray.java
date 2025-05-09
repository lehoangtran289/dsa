package leetcode.dp;

public class M_918_MaximumSumCircularSubarray {
    public static void main(String[] args) {
        System.out.println(maxSubarraySumCircular(new int[]{1, -2, 3, -2})); // 3
    }

    /**
     * Kadane approach
     * -----------------------
     * Idea:
     * maxCircular = max(
     *      max subarray,
     *      total array sum - min subarray,
     * )
     * -----------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxSubarraySumCircular(int[] arr) {
        int totalSum = arr[0];
        int curMax = arr[0], maxSum = arr[0];
        int curMin = arr[0], minSum = arr[0];

        for (int i = 1; i < arr.length; ++i) {
            totalSum += arr[i];

            curMax = Math.max(arr[i], curMax + arr[i]);
            maxSum = Math.max(maxSum, curMax);

            curMin = Math.min(arr[i], curMin + arr[i]);
            minSum = Math.min(minSum, curMin);
        }

        // if all elements are negative
        if (maxSum < 0) return maxSum;

        return Math.max(maxSum, totalSum - minSum);
    }
}
