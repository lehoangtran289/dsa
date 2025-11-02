package leetcode.array.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M_253_MeetingRoomsII {
    public static void main(String[] args) {
        var input = new int[][]{{1, 10}, {2, 7}, {3, 19}, {8, 12}, {10, 20}, {11, 30}};
        System.out.println(minMeetingRooms(input)); // 4
        System.out.println(minMeetingRooms2(input)); // 4
    }

    /**
     * Problem: Given a list of meeting time intervals, find the minimum number of meeting rooms required.
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

        int res = 0;
        int startIdx = 0, endIdx = 0;

        while (startIdx < n) {
            if (starts[startIdx] < ends[endIdx]) {
                res++;
            } else {
                endIdx++;
            }
            startIdx++;
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
