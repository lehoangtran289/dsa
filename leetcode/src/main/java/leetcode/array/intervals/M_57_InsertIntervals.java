package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_57_InsertIntervals {
    static void main() {
        System.out.println(Arrays.deepToString(
                insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}))
        ); // Output: [[1, 5], [6, 9]]
    }

    /**
     * Idea: Find position to add new interval, then perform merge intervals
     * ---
     * TC: O(N)
     * SC: O(N) for result.
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> mergedList = new ArrayList<>();

        // find position to add new interval
        int pos = 0;
        while (pos < n && intervals[pos][0] <= newInterval[0]) {
            mergedList.add(intervals[pos]);
            pos++;
        }

        // add new interval
        if (mergedList.isEmpty()) {
            mergedList.add(newInterval);
        } else {
            int[] last = mergedList.getLast();
            if (last[1] >= newInterval[0]) {
                last[1] = Math.max(last[1], newInterval[1]);
            } else {
                mergedList.add(newInterval);
            }
        }

        // merge remaining intervals in list
        for (int i = pos; i < n; ++i) {
            int[] last = mergedList.getLast();

            if (last[1] >= intervals[i][0]) {
                last[1] = Math.max(last[1], intervals[i][1]);
            } else {
                mergedList.add(intervals[i]);
            }
        }

        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
