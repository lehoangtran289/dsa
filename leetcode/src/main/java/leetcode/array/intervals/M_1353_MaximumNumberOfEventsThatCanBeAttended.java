package leetcode.array.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class M_1353_MaximumNumberOfEventsThatCanBeAttended {
    public static void main(String[] args) {
        System.out.println(maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 2}})); // 4
    }

    /**
     * Greedy approach with min-heap
     * Idea: Attend the event that closes earliest on each day.
     * --------------------
     * TC: O(NlogN) - O(NlogN) (sort) + O(NlogN) (while loop with heap operations)
     * SC: O(N) - heap stores n events
     */
    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        Queue<Integer> minHeap = new PriorityQueue<>();

        int n = events.length;
        int res = 0;
        int i = 0; // current event

        for (int d = 0; d <= 100_000; ++d) {
            // add new event starts at day d
            while (i < n && events[i][0] == d) {
                minHeap.add(events[i][1]);
                i++;
            }

            // remove old events
            while (!minHeap.isEmpty() && minHeap.peek() < d) {
                minHeap.poll();
            }

            // use day d to attend event that closes earliest
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                res++;
            }

            // stop when all events are processed
            if (i >= n && minHeap.isEmpty()) {
                break;
            }
        }

        return res;
    }
}
