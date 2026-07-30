package leetcode.array.intervals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class H_1851_MinimumIntervalToIncludeEachQuery {
    static void main() {
        System.out.println(Arrays.toString(
                minInterval(new int[][]{{1, 4}, {2, 4}, {3, 6}}, new int[]{2, 3, 4, 5})
        )); // Output: [3, 3, 3, 4]
    }

    /**
     * Idea: Line sweep + min heap
     * Sort intervals by start, sort queries, and
     * for each query,
     * Use minHeap to keep track of current min size (till query pos)
     * The top of the heap will be the smallest interval that includes the query.
     * ---
     * TC: O(N log N + Q log Q) where N = number of intervals, Q = number of queries
     * SC: O(N + Q) for result map and heap
     */
    public static int[] minInterval(int[][] intervals, int[] queries) {
        // store queries' indices so we can map it back to result later
        Map<Integer, Integer> resultMap = new HashMap<>();
        int[] processedQueries = queries.clone();
        Arrays.sort(processedQueries);

        // sort intervals by start
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // line sweep query position + min heap for size
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // [size, end]
        int intervalIdx = 0;

        for (int queryPos : processedQueries) {
            while (intervalIdx < intervals.length && intervals[intervalIdx][0] <= queryPos) {
                int start = intervals[intervalIdx][0];
                int end = intervals[intervalIdx][1];
                int size = end - start + 1;

                minHeap.add(new int[]{size, end});
                intervalIdx++;
            }

            // lazy deletion
            while (!minHeap.isEmpty() && minHeap.peek()[1] < queryPos) {
                minHeap.poll();
            }

            resultMap.put(queryPos, minHeap.isEmpty() ? -1 : minHeap.peek()[0]);
        }

        // build result
        int[] res = new int[queries.length];
        int i = 0;

        for (int query : queries) {
            res[i++] = resultMap.get(query);
        }

        return res;
    }
}
