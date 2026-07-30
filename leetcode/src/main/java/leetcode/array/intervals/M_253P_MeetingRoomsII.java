package leetcode.array.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Problem: Given a list of meeting time intervals, find the minimum number of meeting rooms required.
 */
public class M_253P_MeetingRoomsII {
    static void main() {
        var input = new int[][]{{1, 10}, {2, 7}, {3, 19}, {8, 12}, {10, 20}, {11, 30}};
        System.out.println(minMeetingRooms(input)); // 4
        System.out.println(minMeetingRooms2(input)); // 4
    }

    /**
     * Idea: Use a difference array && prefix sum
     * ---
     * TC: O(n log n) - due to sorting
     * SC: O(n)
     */
    public static int minMeetingRooms3(int[][] intervals) {
        int maxEnd = 0;

        for (int[] interval : intervals) {
            maxEnd = Math.max(maxEnd, interval[1]);
        }

        int[] diff = new int[maxEnd + 1];
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int[] interval : intervals) {
            diff[interval[0]]++;
            diff[interval[1]]--;
        }

        int res = 0;
        int curSum = 0;
        for (int num : diff) {
            curSum += num;
            res = Math.max(res, curSum);
        }

        return res;
    }

    /**
     * Idea: Line sweep, iterate through start and end times separately.
     * -----------------------
     * TC: O(n log n) - due to sorting
     * SC: O(n)
     */
    public static int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; ++i) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int startPtr = 0, endPtr = 0;
        int res = 0;

        while (startPtr < intervals.length) {
            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (starts[startPtr] >= ends[endPtr]) {
                res -= 1;
                endPtr += 1;
            }

            // We do this irrespective of whether a room frees up or not.
            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
            // remain the same in that case. If no room was free, then this would increase used_rooms
            res += 1;
            startPtr += 1;
        }

        return res;
    }

    /**
     * Idea: Use a priority queue to track the end times of meetings.
     * -----------------------
     * TC: O(n log n) - due to sorting and priority queue operations
     * SC: O(n)
     */
    public static int minMeetingRooms2(int[][] intervals) {
        // sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // heap to store min ending times
        var minHeap = new PriorityQueue<Integer>();

        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }

            minHeap.add(interval[1]);
        }

        return minHeap.size();
    }
}
