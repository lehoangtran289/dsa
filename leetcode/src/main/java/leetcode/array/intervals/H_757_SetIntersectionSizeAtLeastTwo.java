package leetcode.array.intervals;

import java.util.Arrays;

public class H_757_SetIntersectionSizeAtLeastTwo {
    public static void main(String[] args) {
        System.out.println(intersectionSizeTwo(new int[][]{
                {1, 3},
                {1, 4},
                {2, 5},
                {3, 5}
        })); // 3
    }

    /**
     * Greedy
     * Idea: Sort intervals by end time, then greedily select points from the end
     * ------------------
     * TC: O(n log n)
     * SC: O(1)
     */
    public static int intersectionSizeTwo(int[][] intervals) {
        // prefer smaller intervals
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);

        int res = 0;
        int secondLast = -1, last = -1;

        for (int[] interval : intervals) {
            // interval contains both points, range (-inf, secondLast]
            if (interval[0] <= secondLast) continue;

            // interval contains 1 last point, range (secondLast, last]
            if (interval[0] <= last) {
                res++;
                secondLast = last;
                last = interval[1];
            } else { // interval not contain any point
                res += 2;
                secondLast = interval[1] - 1;
                last = interval[1];
            }
        }

        return res;
    }
}
