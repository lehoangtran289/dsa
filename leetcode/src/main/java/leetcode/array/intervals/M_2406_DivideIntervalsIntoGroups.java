package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class M_2406_DivideIntervalsIntoGroups {
    public static void main(String[] args) {
        int[][] intervals = {{5, 10}, {6, 8}, {1, 5}, {2, 3}, {1, 10}};
        int[][] intervals2 = {{1, 1}};
        System.out.println(new M_2406_DivideIntervalsIntoGroups().minGroups(intervals2));
    }

    // find concurrent intervals at any time event
    public int minGroups(int[][] inters) {
        // create events list and sort by start time [(start[i], 1), (end[i], -1)]
        List<int[]> events = new ArrayList<>();
        for (int[] inter : inters) {
            events.add(new int[]{inter[0], 1});
            events.add(new int[]{inter[1], -1});
        }
        events.sort((a, b) -> {
            if (a[0] == b[0]) return -Integer.compare(a[1], b[1]); // 1 then -1
            else return Integer.compare(a[0], b[0]);
        });

        // traverse event list to find max concurrent intervals at a timestamp
        int result = 0;
        int concurrentIntervals = 0;
        for (int[] event : events) {
            concurrentIntervals += event[1];
            result = Math.max(result, concurrentIntervals);
        }
        return result;
    }

    public int minGroups2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (pq.isEmpty()) {
                pq.add(end);
            } else {
                if (pq.peek() <= start) {
                    pq.poll();
                }
                pq.add(end);
            }
        }
        return pq.size();
    }


    // TLE -> O(n^2)
    public int minGroupsTLE(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        boolean[] isGrouped = new boolean[intervals.length];

        int res = 0;
        for (int i = 0; i < intervals.length; ++i) {
            if (isGrouped[i]) continue;
            isGrouped[i] = true;
            int end = intervals[i][1];

            for (int j = i + 1; j < intervals.length; ++j) {
                if (!isGrouped[j] && end < intervals[j][0]) {
                    isGrouped[j] = true;
                    end = intervals[j][1];
                }
            }
            res++;
        }
        return res;
    }
}
