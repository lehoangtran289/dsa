package leetcode.array.slidingwindow;

public class E_674_LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(findLengthOfLCIS(new int[]{1, 3, 5, 4, 7})); // 3
    }

    /**
     * Sliding window
     */
    public static int findLengthOfLCIS(int[] nums) {
        int res = 1;
        int l = 0;
        for (int r = 1; r < nums.length; ++r) {
            if (nums[r] <= nums[r - 1]) l = r;
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    /**
     * Counting
     */
    public static int findLengthOfLCIS2(int[] nums) {
        int res = 1;
        int count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                count++;
                res = Math.max(res, count);
            }
            else count = 1;
        }
        return res;
    }
}
