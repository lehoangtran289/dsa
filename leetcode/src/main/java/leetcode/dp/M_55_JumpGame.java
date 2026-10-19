package leetcode.dp;

public class M_55_JumpGame {

    static void main() {
        M_55_JumpGame sol = new M_55_JumpGame();
        System.out.println(sol.canJump(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(sol.canJump(new int[]{3, 2, 1, 0, 4})); // false
    }

    /**
     * Idea: Greedy. Check furthest index a position can reach.
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxIndex = 0;

        for (int i = 0; i < n; ++i) {
            if (maxIndex < i) return false;
            maxIndex = Math.max(maxIndex, nums[i] + i);
        }

        return true;
    }


    /**
     * Bottom up DP approach
     * state = is valid jump at i-th step
     * dp[i] = dp[k] for i + 1 <= k <= min(n - 1, i + nums[i])
     * ------------------
     * TC O(n^2)
     * SC O(n)
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;

        for (int i = 0; i < n; ++i) {
            if (!dp[i]) continue; // unreachable

            for (int j = i + 1; j <= Math.min(nums[i] + i, n - 1); ++j) {
                dp[j] = dp[i];
            }
        }

        return dp[n - 1];
    }
}
