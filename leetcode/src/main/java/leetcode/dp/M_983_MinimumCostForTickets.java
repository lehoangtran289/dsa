package leetcode.dp;

import java.util.*;

public class M_983_MinimumCostForTickets {
    public static void main(String[] args) {
        System.out.println(mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15})); // 11
    }

    /**
     * DP bottom up approach
     * Idea:
     * dp[i] = min cost to travel until day i-th
     * - if days not contains i -> dp[i] = dp[i - 1]
     * - else -> dp[i] = min(dp[i - 1] + costs[0], dp[i - 7] + costs[1], dp[i - 30] + costs[2])
     * ---------------------
     * TC: O(N)
     */
    public static int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];

        Set<Integer> daySet = new HashSet<>();
        for (int d : days) {
            daySet.add(d);
        }

        for (int d = 1; d <= lastDay; ++d) {
            if (daySet.contains(d)) {
                dp[d] = Math.min(
                        dp[d - 1] + costs[0],
                        Math.min(
                                dp[Math.max(0, d - 7)] + costs[1],
                                dp[Math.max(0, d - 30)] + costs[2]
                        )
                );
            } else {
                dp[d] = dp[d - 1];
            }
        }

        return dp[lastDay];
    }
}
