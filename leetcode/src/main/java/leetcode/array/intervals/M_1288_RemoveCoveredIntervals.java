package leetcode.array.intervals;

import java.util.Arrays;

public class M_1288_RemoveCoveredIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {3, 6}, {2, 8}};
        System.out.println(removeCoveredIntervals(intervals)); // Output: 2
    }

    /**
     * Greedy. Idea: Sort by start point ASC so that you don't have to care about it, then end point DESC
     * ---
     * TC: O(n log n)
     * SC: O(1)
     */
    public static int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int res = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < n; ++i) {
            int curEnd = intervals[i][1];
            if (curEnd <= prevEnd) {
                res++;
            } else {
                prevEnd = curEnd;
            }
        }

        return n - res;
    }
}
