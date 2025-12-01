package leetcode.dp;

import java.util.Arrays;

public class H_1235_MaximumProfitInJobScheduling {

    /**
     * DP Knapsack + Binary Search
     * ------------------------------
     * skip = dp[i - 1]
     * take = dp[prev] + profit[i], where prev = lastest non-overlapping task
     * ------------------------------
     * Note: unlike a traditional knapsack problem, here the constraint is non-overlapping tasks,
     * instead of budgeting processing unit time
     * -> So we must sort tasks first, then find the lastest non-overlapping task
     * ------------------------------
     * TC: O(n log n) due to sorting + binary search
     * SC: O(n)
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Task[] tasks = new Task[n];

        // init task list and sort by end time ASC
        for (int i = 0; i < n; ++i) {
            tasks[i] = new Task(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(tasks, (a, b) -> a.end - b.end);

        // dp bottom up - max profit using first i tasks
        int[] dp = new int[n];
        dp[0] = tasks[0].profit;

        for (int i = 1; i < n; ++i) {
            Task cur = tasks[i];

            // Choice 1: skip
            int skip = dp[i - 1];

            // Choice 2: take
            int take = cur.profit;
            int prev = binSearch(tasks, cur.start);
            if (prev != -1) take += dp[prev];

            // next dp state
            dp[i] = Math.max(skip, take);
        }

        return dp[n - 1];
    }

    private int binSearch(Task[] tasks, int target) {
        int res = -1;
        int l = 0, r = tasks.length - 1;

        while (l <= r) {
            int mid = r - (r - l) / 2;

            if (tasks[mid].end <= target) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    static class Task {
        int start;
        int end;
        int profit;

        Task(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}
