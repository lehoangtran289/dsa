package leetcode.dp;

public class M_55_JumpGame {

    public static void main(String[] args) {
        M_55_JumpGame sol = new M_55_JumpGame();
        System.out.println(sol.canJump(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(sol.canJump(new int[]{3, 2, 1, 0, 4})); // false
    }

    /**
     * Top down DP approach
     * state = is valid jump at i-th step
     * dp(i) = dp(k) for i + 1 <= k <= min(n - 1, i + nums[i])
     * ------------------
     * TC O(n^2)
     * SC O(n)
     */
    private int[] nums;
    private Boolean[] memo;

    public boolean canJump(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        this.memo = new Boolean[n];

        memo[n - 1] = true;
        return dp(0);
    }

    private boolean dp(int pos) {
        if (memo[pos] != null) return memo[pos];

        int maxJumpPos = Math.min(nums.length - 1, pos + nums[pos]);

        for (int i = pos + 1; i <= maxJumpPos; ++i) {
            if (dp(i)) {
                return memo[pos] = true;
            }
        }

        return memo[pos] = false;
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
        Boolean[] dp = new Boolean[n];
        dp[n - 1] = true;

        for (int i = n - 2; i >= 0; --i) {
            int maxJumpPos = Math.min(n - 1, i + nums[i]);

            boolean res = false;
            for (int j = i + 1; j <= maxJumpPos; ++j) {
                if (dp[j]) {
                    res = true;
                    break;
                }
            }
            dp[i] = res;
        }

        return dp[0];
    }
}
