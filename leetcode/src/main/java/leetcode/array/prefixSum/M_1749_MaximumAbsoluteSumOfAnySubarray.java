package leetcode.array.prefixSum;

public class M_1749_MaximumAbsoluteSumOfAnySubarray {
    public static void main(String[] args) {
        System.out.println(maxAbsoluteSum(new int[]{-7, -1, 0, -2, 1, 3, 8, -2, -6, -1, -10, -6, -6, 8, -4, -9, -4, 1, 4, -9})); // 44
        System.out.println(maxAbsoluteSum(new int[]{1, -3, 2, 3, -4})); // 5
        System.out.println(maxAbsoluteSum(new int[]{2, -5, 1, -4, 3, -2})); // 8
    }

    public static int maxAbsoluteSum(int[] nums) {
        int curSum = 0;
        int maxSum = 0;
        int minSum = 0;
        int res = 0;

        for (int num : nums) {
            curSum += num;
            maxSum = Math.max(maxSum, curSum);
            minSum = Math.min(minSum, curSum);

            res = Math.max(res, Math.abs(maxSum - minSum));
        }

        return res;
    }
}
