package leetcode.array;

public class M_2270_NumberOfWaysToSplitArray {
    public static void main(String[] args) {
        System.out.println(waysToSplitArray(new int[]{2, 3, 1, 0}));
    }

    public static int waysToSplitArray(int[] nums) {
        int n = nums.length;

        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int res = 0;
        for (int i = 1; i <= n - 1; ++i) {
            long left = prefixSum[i];
            long right = prefixSum[n] - prefixSum[i];
            if (left >= right) res++;
        }

        return res;
    }
}
