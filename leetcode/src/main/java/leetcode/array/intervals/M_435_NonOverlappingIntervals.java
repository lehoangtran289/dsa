package leetcode.array.intervals;

import java.util.Arrays;

public class M_435_NonOverlappingIntervals {
    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 2}, {3, 4}, {2, 3}, {1, 3}})); // 1
    }

    /**
     * Greedy approach
     * Idea: sort by end time -> if start time >= curEnd -> no overlap
     * --------------------------
     * TC: O(nlogn) -> sort
     * SC: O(1)
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        // sort by end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int res = 0;
        int curEnd = Integer.MIN_VALUE;

        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];

            if (start >= curEnd) {
                curEnd = end;
            } else {
                res++;
            }
        }

        return res;
    }

    /**
     * Idea: sort by start time -> if prev end > start -> overlap -> remove one with larger end
     */
    public static int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int res = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; ++i) {
            int start = intervals[i][0], end = intervals[i][1];

            if (prevEnd > start) { // overlap -> remove one with larger end
                prevEnd = Math.min(end, prevEnd);
                res++;
            } else {
                prevEnd = end;
            }
        }

        return res;
    }
}
