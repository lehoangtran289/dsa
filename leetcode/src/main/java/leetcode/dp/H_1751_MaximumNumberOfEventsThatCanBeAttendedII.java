package leetcode.dp;

import java.util.Arrays;

/**
 * Similar to problem 1235, but with additional constraint (maximum k events)
 */
public class H_1751_MaximumNumberOfEventsThatCanBeAttendedII {

    /**
     * Idea: DP Knapsack + Binary Search
     * dp[i][j] = max value considering first i events, with max j events
     * ---
     * TC: O(n * logn + n * k)
     * SC: O(n * k)
     */
    public int maxValue(int[][] events, int k) {
        int n = events.length;

        // sort events by end day
        Arrays.sort(events, (a, b) -> Integer.compare(a[1], b[1]));

        // dp[i][j] = max value considering first i events, with max j events
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; ++i) {
            int[] event = events[i - 1];
            int prevEvent = getPreviousEvent(events, event[0]);
            int prevId = prevEvent + 1;

            for (int j = 1; j <= k; ++j) {
                // skip
                int skip = dp[i - 1][j];

                // take
                int take = dp[prevId][j - 1] + event[2]; // if prevEvent = -1 -> prevId = 0 -> dp = 0

                dp[i][j] = Math.max(skip, take);
            }
        }
        return dp[n][k];
    }

    private int getPreviousEvent(int[][] events, int targetEnd) {
        int res = -1;
        int l = 0, r = events.length - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (events[mid][1] < targetEnd) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }
}
