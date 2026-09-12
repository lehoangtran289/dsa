package leetcode.dp;

import java.util.Arrays;

public class H_1235_MaximumProfitInJobScheduling {
    static void main() {
        System.out.println(jobScheduling(
                new int[]{1, 2, 3, 4, 6},
                new int[]{3, 5, 10, 6, 9},
                new int[]{20, 20, 100, 70, 60}
        )); // 150
    }

    /**
     * DP Knapsack + Binary Search
     * ------------------------------
     * dp[i] = max profit when considering jobs 0...i
     * = max(
     *      skip = dp[i - 1],
     *      take = dp[prev] + profit[i], where prev = lastest non-overlapping task
     * )
     * ------------------------------
     * Note: unlike a traditional knapsack problem, here the constraint is non-overlapping tasks,
     * instead of budgeting processing unit time
     * -> So we must sort tasks first, then find the lastest non-overlapping task
     * ------------------------------
     * TC: O(n log n) due to sorting + binary search
     * SC: O(n)
     */
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];

        // init jobs array and sort by endtime ASC
        for (int i = 0; i < n; ++i) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> Integer.compare(a.end, b.end));

        // init dp states: dp[i] = max profit when considering jobs 0...i
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; ++i) {
            Job cur = jobs[i];

            // Option 1: skip
            int skip = dp[i - 1];

            // Option 2: take
            int take = cur.profit;
            int prev = getPreviousJob(jobs, cur.start);
            if (prev != -1) take += dp[prev];

            // update dp state
            dp[i] = Math.max(skip, take);
        }

        return dp[n - 1];
    }

    private static int getPreviousJob(Job[] jobs, int target) {
        int res = -1;
        int l = 0, r = jobs.length - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (jobs[mid].end <= target) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    static class Job {
        int start;
        int end;
        int profit;

        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}
