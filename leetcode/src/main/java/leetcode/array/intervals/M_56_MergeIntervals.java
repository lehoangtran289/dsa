package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_56_MergeIntervals {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(
                merge(new int[][]{{2, 6}, {1, 3}, {8, 10}, {15, 18}}))
        ); // [[1, 6], [8, 10], [15, 18]]
    }

    /**
     * Merge Intervals
     * ----------------------------------
     * TC: O(nlogn) - sorting
     * SC: O(n)
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> mergedList = new ArrayList<>();
        mergedList.add(intervals[0]);

        for (int[] interval : intervals) {
            int[] cur = mergedList.get(mergedList.size() - 1);

            if (cur[1] >= interval[0]) {
                cur[1] = Math.max(cur[1], interval[1]);
            } else {
                mergedList.add(interval);
            }
        }

        int n = mergedList.size();
        int[][] res = new int[n][2];

        for (int i = 0; i < n; ++i) {
            res[i] = mergedList.get(i);
        }

        return res;
    }
}
