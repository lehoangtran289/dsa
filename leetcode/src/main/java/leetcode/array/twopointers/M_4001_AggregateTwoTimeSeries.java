package leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_4001_AggregateTwoTimeSeries {

    /**
     * 2 pointers
     * ---
     * TC: O(n + m)
     * SC: O(n + m)
     */
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        int len1 = series1.length, len2 = series2.length;
        List<List<Integer>> res = new ArrayList<>();

        int p1 = 0, p2 = 0;

        while (p1 < len1 && p2 < len2) {
            int timestamp = Math.min(series1[p1][0], series2[p2][0]);
            int value = series1[p1][1] + series2[p2][1];

            res.add(Arrays.asList(timestamp, value));

            if (timestamp == series1[p1][0]) p1++;
            if (timestamp == series2[p2][0]) p2++;
        }

        while (p1 < len1) {
            res.add(Arrays.asList(series1[p1][0], series1[p1][1]));
            p1++;
        }

        while (p2 < len2) {
            res.add(Arrays.asList(series2[p2][0], series2[p2][1]));
            p2++;
        }

        return res;
    }
}
