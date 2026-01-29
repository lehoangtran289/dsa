package leetcode.greedy;

import java.util.Arrays;

public class M_452_MinimumNumberOfArrowsToBurstBalloons {

    /**
     * Greedy
     * Idea: Sort the balloons by their ending x-coordinate. Use a greedy approach to shoot arrows.
     * Proof:
     * - By always shooting an arrow at the end of the first balloon,
     * we maximize the chance of bursting subsequent balloons that overlap with it.
     * - If a balloon starts after the current arrow's position, we need a new arrow.
     * ----------------------------------
     * TC: O(n log n) due to sorting
     * SC: O(1)
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int res = 1;
        int curEnd = points[0][1];

        for (int[] point : points) {
            if (point[0] > curEnd) {
                res++;
                curEnd = point[1];
            }
        }

        return res;
    }

    /**
     * Greedy - Alternative Implementation
     */
    public int findMinArrowShots2(int[][] points) {
        int n = points.length;
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int res = 0;

        int i = 0;
        while (i < n) {
            res++;
            int curEnd = points[i][1];

            while (i < n && points[i][0] <= curEnd && curEnd <= points[i][1]) {
                i++;
            }
        }

        return res;
    }
}
