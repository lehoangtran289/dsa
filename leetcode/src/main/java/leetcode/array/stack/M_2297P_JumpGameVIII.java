package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class M_2297P_JumpGameVIII {
    static void main() {
        System.out.println(minCost(new int[]{3, 2, 4, 4, 1}, new int[]{3, 7, 6, 4, 2})); // 8
    }

    /**
     * Idea: build next greater & next smaller element for each index -> that's the reachable indices of each position
     * Then use DP to find min cost
     * ---
     * TC: O(3N)
     * SC: O(3N)
     */
    public static long minCost(int[] nums, int[] costs) {
        int n = nums.length;

        List<Integer>[] reachable = new List[n];
        for (int i = 0; i < n; ++i) reachable[i] = new ArrayList<>();

        Deque<Integer> stack = new ArrayDeque<>();

        // 1. build next greater element
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                reachable[stack.pop()].add(i);
            }
            stack.push(i);
        }

        // 2. build next smaller element
        stack.clear();
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                reachable[stack.pop()].add(i);
            }
            stack.push(i);
        }

        // 3. DP
        long[] dp = new long[n];
        Arrays.fill(dp, 1L << 60);
        dp[0] = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < reachable[i].size(); ++j) {
                int reachableIndex = reachable[i].get(j);
                dp[reachableIndex] = Math.min(dp[reachableIndex], dp[i] + costs[reachableIndex]);
            }
        }

        return dp[n - 1];
    }
}
