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
     * TC: O(n + m) - n meetings, m = max range
     * SC: O(n)
     */
    public static int minMeetingRooms3(int[][] intervals) {
        int[] diffArray = new int[1000001];

        for (int[] interval : intervals) {
            diffArray[interval[0]]++;
            diffArray[interval[1]]--;
        }

        int res = 0;
        int prefixSum = 0;

        for (int diff : diffArray) {
            prefixSum += diff;
            res = Math.max(res, prefixSum);
        }

        return res;
    }

    /**
     * Idea: Use a priority queue to track the end times of meetings, compare with each interval start time
     * If current start time > nearest end time -> assign that room to current interval
     * Else allocate another room for current interval
     * -----------------------
     * TC: O(n log n) - due to sorting and priority queue operations
     * SC: O(n)
     */
    public static int minMeetingRooms2(int[][] intervals) {
        // sort intervals by start time ASC
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // min heap to store end time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            int curStart = interval[0], curEnd = interval[1];

            // if nearest end time < current start time -> assign that room for current interval
            if (!minHeap.isEmpty() && minHeap.peek() <= curStart) {
                minHeap.poll();
            }
            minHeap.add(curEnd);
        }
        return minHeap.size();
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
}
