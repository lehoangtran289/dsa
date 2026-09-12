package leetcode.dp;

import java.util.Arrays;

/**
 * Similar to problem 1235, but with additional constraint (maximum k events)
 */
public class H_1751_MaximumNumberOfEventsThatCanBeAttendedII {

    /**
     * Idea: DP Knapsack + Binary Search
     * dp[i][j] = max value considering events [0, i], with max j events
     * ---
     * TC: O(n * logn + n * k)
     * SC: O(n * k)
     */
    public int maxValue(int[][] events, int k) {
        int n = events.length;

        // sort events by end day
        Arrays.sort(events, (a, b) -> Integer.compare(a[1], b[1]));

        // dp[i][j] = max value considering events [0, i], with max j events
        int[][] dp = new int[n][k + 1];

        // init dp states
        for (int i = 1; i <= k; ++i) {
            dp[0][i] = events[0][2];
        }

        for (int i = 1; i < n; ++i) {
            int prevEvent = getPreviousEvent(events, events[i][0]);

            for (int j = 1; j <= k; ++j) {
                // skip
                int skip = dp[i - 1][j];

                // take
                int take = events[i][2];
                if (prevEvent != -1) take += dp[prevEvent][j - 1];

                dp[i][j] = Math.max(skip, take);
            }
        }

        int res = 0;
        for (int i = 1; i <= k; ++i) {
            res = Math.max(res, dp[n - 1][i]);
        }
        return res;
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
