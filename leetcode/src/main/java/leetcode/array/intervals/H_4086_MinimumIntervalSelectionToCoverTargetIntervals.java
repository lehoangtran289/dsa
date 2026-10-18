package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class H_4086_MinimumIntervalSelectionToCoverTargetIntervals {
    static void main() {
        System.out.println(minSelection(new int[][]{{1, 3}, {4, 6}}, new int[][]{{1, 3}, {2, 6}})); // -1
    }

    /**
     * Idea: Greedy. Merge intervals for target intervals.
     * Then for each target interval, we greedily choose each interval in list of available intervals that has start point <= target start point,
     * to see which interval has the maximum end point.
     * ---
     * N = max(intervals.length, targetIntervals.length)
     * TC: O(N log N) - sorting by start point
     * SC: O(N)
     */
    public static int minSelection(int[][] intervals, int[][] targetIntervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(targetIntervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> mergedTargetIntervals = merge(targetIntervals);

        int res = 0;
        int index = 0;
        int covered = 0;

        for (int[] target : mergedTargetIntervals) {
            int targetLeft = target[0], targetRight = target[1];

            int maxCovered = covered;

            // can we choose an interval that has start point <= already covered part, with maximum end point
            // invalid if we cannot move ptr forward
            while (covered < targetRight) {
                int targetToReach = Math.max(targetLeft, covered);

                while (index < n && intervals[index][0] <= targetToReach) {
                    maxCovered = Math.max(maxCovered, intervals[index][1]);
                    index++;
                }

                // the best option doesn’t get past where I already am => invalid
                // why <= ? we set covered = maxCovered, and in next iteration maxCovered cannot get past covered -> stuck
                if (maxCovered <= covered) return -1;
                covered = maxCovered;
                res++;
            }
        }

        return res;
    }

    private static List<int[]> merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int[] curInterval : intervals) {
            int[] lastInterval = result.getLast();

            if (lastInterval[1] >= curInterval[0]) {
                lastInterval[1] = Math.max(lastInterval[1], curInterval[1]);
            } else {
                result.add(curInterval);
            }
        }

        return result;
    }
}
