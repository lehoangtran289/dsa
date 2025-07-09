package leetcode.array.prefixSum;

public class M_334_IncreasingTripletSubsequence {

    /**
     * Prefix Sum
     * Idea: create 2 arrays to store minimum and maximum values before and after each index
     * -------------------
     * TC: O(n)
     * SC: O(n)
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;

        // base case
        if (n < 3) return false;

        // create 2 arrays to store min before i-th and max after i-th (1 <= i <= n - 2)
        int[] minPrefix = new int[n];
        int[] maxPostfix = new int[n];
        minPrefix[0] = nums[0];
        maxPostfix[n - 1] = nums[n - 1];

        for (int i = 1; i < n; ++i) {
            minPrefix[i] = Math.min(minPrefix[i - 1], nums[i]);
        }

        for (int i = n - 2; i >= 0; --i) {
            maxPostfix[i] = Math.max(maxPostfix[i + 1], nums[i]);
        }

        // check if there exists an index i that satisfies the condition
        for (int i = 1; i < n - 1; ++i) {
            if (minPrefix[i] < nums[i] && nums[i] < maxPostfix[i]) return true;
        }

        return false;
    }
}
