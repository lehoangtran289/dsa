package leetcode.dp;

public class M_45_JumpGameII {

    /**
     * Idea: greedy. [l, r] = segment reachable by "res" jumps so far.
     * For each elemt in [l, r] we find furthest index this segment can reach
     * Forward to next segment [r + 1, furthest] ~ add 1 more jump
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public static int jump2(int[] nums) {
        int n = nums.length;
        int res = 0;

        int l = 0, r = 0;
        while (r < n - 1) { // if r = n - 1, we reach the dest -> no need to process the loop
            int furthest = 0;
            for (int i = l; i <= r; ++i) {
                furthest = Math.max(furthest, nums[i] + i);
            }
            l = r + 1;
            r = furthest;
            res++;
        }

        return res;
    }


    /**
     * Idea: dp[i] = min steps to reach index i
     * ---
     * TC: O(N^2)
     * SC: O(N)
     */
    public static int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j <= Math.min(nums[i] + i, n - 1); ++j) {
                if (dp[j] > 0) dp[j] = Math.min(dp[j], dp[i] + 1);
                else dp[j] = dp[i] + 1;
            }
        }

        return dp[n - 1];
    }
}
