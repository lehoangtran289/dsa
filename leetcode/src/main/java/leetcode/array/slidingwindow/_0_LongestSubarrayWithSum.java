package leetcode.array.slidingwindow;

public class _0_LongestSubarrayWithSum {
    public static void main(String[] args) {
        System.out.println(longestSubarrayWithSum(new int[]{1, 2, 1, 1, 1}, 3)); // 3
    }

    // Sliding window technique
    public static int longestSubarrayWithSum(int[] nums, int k) {
        int n = nums.length;

        int res = 0;
        int curSum = 0;
        int l = 0;

        for (int r = 0; r < n; ++r) {
            curSum += nums[r];

            while (curSum > k) {
                curSum -= nums[l];
                ++l;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
