package leetcode.divideconquer;

public class M_53_MaximumSubarray {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 23
    }

    public static int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length - 1);
    }

    public static int maxSubArray(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];
        int mid = (hi + lo) / 2;
        int lsum = maxSubArray(nums, lo, mid);
        int rsum = maxSubArray(nums, mid + 1, hi);
        int cross = crossSum(nums, lo, hi, mid);
        return Math.max(Math.max(lsum, rsum), cross);
    }

    private static int crossSum(int[] nums, int lo, int hi, int mid) {
        int lsum = 0;
        int lmax = Integer.MIN_VALUE;
        for (int i = mid; i >= lo; i--) {
            lsum += nums[i];
            lmax = Math.max(lmax, lsum);
        }

        int rsum = 0;
        int rmax = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= hi; i++) {
            rsum += nums[i];
            rmax = Math.max(rsum, rmax);
        }
        return lmax + rmax;
    }

}
