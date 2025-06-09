package leetcode.array.intervals;

import java.util.*;

public class M_253_MeetingRoomsII {

    /**
     * Problem: Given a list of meeting time intervals, find the minimum number of meeting rooms required.
     * Idea: Line sweep, iterate through start and end times separately.
     * -----------------------
     * TC: O(n log n) - due to sorting
     * SC: O(n)
     */
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; ++i) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int res = 0;
        int endIndex = 0;

        for (int i = 0; i < n; ++i) {
            if (start[i] < end[endIndex]) {
                res++; // A new meeting starts before the previous one ends, so we need a new room
            } else {
                endIndex++; // The previous meeting has ended, so we can reuse that room
            }
        }

        return res;
    }
}
