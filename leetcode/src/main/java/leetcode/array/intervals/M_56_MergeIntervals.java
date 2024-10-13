package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for (int[] interval : intervals) {
            int[] cur = result.get(result.size() - 1);
            if (cur[1] >= interval[0]) {
                cur[1] = Math.max(cur[1], interval[1]);
            } else {
                result.add(interval);
            }
        }

        int[][] ret = new int[result.size()][];
        for (int i = 0; i < result.size(); ++i) {
            ret[i] = result.get(i);
        }
        return ret;
    }

    /**
     * naive approach -> low readability
     */
    public int[][] mergeV1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        int i = 0;
        while (i < intervals.length) {
            int[] cur = intervals[i];
            int[] temp = new int[2];
            temp[0] = cur[0];
            temp[1] = cur[1];

            int k = i + 1;
            while (k < intervals.length) {
                if (intervals[k][0] <= temp[1]) {
                    temp[1] = Math.max(intervals[k][1], temp[1]);
                    ++k;
                    i = k - 1;
                } else {
                    break;
                }
            }
            result.add(temp);
            ++i;
        }

        int[][] ret = new int[result.size()][];
        for (int j = 0; j < result.size(); ++j) {
            ret[j] = result.get(j);
        }
        return ret;
    }
}
