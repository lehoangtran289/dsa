package leetcode.array.slidingwindow;

import java.util.Arrays;

public class M_2747_CountZeroRequestServers {

    /**
     * Idea: sort logs and queries by time ASC, then use sliding window through queries
     * ---
     * TC: O(Q + QlogQ + LlogL + Q + L) = O(QlogQ + LlogL)
     * SC: O(Q + n)
     */
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int[] res = new int[queries.length];

        // store original query indices - O(Q)
        int queryCount = queries.length;
        int[][] queriesWithIndex = new int[queryCount][2];
        for (int i = 0; i < queryCount; ++i) {
            queriesWithIndex[i] = new int[]{queries[i], i};
        }

        // sort logs and queries by timestamp ASC - O(QlogQ + LlogL)
        Arrays.sort(queriesWithIndex, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // init sliding window states
        int[] serverIdCounts = new int[n + 1]; // instead of Map<int, int> for frequencies
        int activeServers = 0;

        int l = 0, r = 0;

        for (int[] query : queriesWithIndex) { // O(Q + L)
            int startTime = query[0] - x, endTime = query[0];
            int queryIndex = query[1];

            // expand right pointer
            while (r < logs.length && logs[r][1] <= endTime) {
                serverIdCounts[logs[r][0]]++;
                if (serverIdCounts[logs[r][0]] == 1) activeServers++;
                r++;
            }

            // shrink left pointer
            while (l < r && logs[l][1] < startTime) {
                serverIdCounts[logs[l][0]]--;
                if (serverIdCounts[logs[l][0]] == 0) activeServers--;
                l++;
            }

            res[queryIndex] = n - activeServers;
        }

        return res;
    }
}
