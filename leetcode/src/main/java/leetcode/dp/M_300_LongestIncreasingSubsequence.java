package leetcode.dp;

public class M_300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        M_300_LongestIncreasingSubsequence obj = new M_300_LongestIncreasingSubsequence();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(obj.lengthOfLIS(nums)); // 4
    }

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     * Dynamic programming approach.
     * LIS[i] = 1 OR LIS[j] + 1 for j < i and nums[j] < nums[i], where LIS[j] is the length of the longest increasing subsequence ending at index j.
     */
    public int lengthOfLIS(int[] nums) {
        // LIS[i] = length of the longest increasing subsequence ending at index i
        // initialize LIS[i] = 1 for all i
        int[] LIS = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            LIS[i] = 1;
        }

        // LIS[i] = max(LIS[j] + 1) for all j < i and nums[j] < nums[i]
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    LIS[i] = Math.max(LIS[j] + 1, LIS[i]);
                }
            }
        }

        // find max(LIS[i])
        int max = -1;
        for (int n : LIS) {
            max = Math.max(max, n);
        }
        return max;
    }
}
