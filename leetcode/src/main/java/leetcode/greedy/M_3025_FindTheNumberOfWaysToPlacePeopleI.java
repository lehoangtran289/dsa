package leetcode.greedy;

import java.util.Arrays;

/**
 * Sane problem: 3027. Find the Number of Ways to Place People II
 */
public class M_3025_FindTheNumberOfWaysToPlacePeopleI {

    /**
     * Sorting + greedy
     * -------------
     * Time: O(n^2)
     * Space: O(1)
     */
    public int numberOfPairs(int[][] points) {
        // Sort by x ascending, then y descending
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));

        int res = 0;

        // for each upper left point
        for (int i = 0; i < points.length - 1; i++) {
            int[] pointA = points[i];
            int yMin = Integer.MIN_VALUE;
            int yMax = pointA[1];

            // * check possible bottom right points
            // since points are sorted by x -> should not care about comparing x_A and x_B, and yMax is static
            // when choosing B2, it should higher (>) than the last B1 to ensure the property that B_j is in bottom right,
            //      and between A and B_j there is no other points
            for (int j = i + 1; j < points.length; j++) {
                int[] pointB = points[j];

                if (pointB[1] > yMin && pointB[1] <= yMax) {
                    res++;
                    yMin = pointB[1];
                }
            }
        }
        return res;
    }

    /**
     * Simulation - Brute Force
     * -------------
     * Time: O(n^3)
     * Space: O(1)
     */
    public int numberOfPairs1(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                int[] left = points[i];
                int[] right = points[j];

                // check if A is on the upper left side of B
                if (
                        (left[0] > right[0] && left[1] > right[1])
                        || (left[0] < right[0] && left[1] < right[1])
                ) continue;

                // check if exists any points between A & B rectangle
                boolean isValidPair = true;
                for (int k = 0; k < points.length; ++k) {
                    if (k == i || k == j) continue;
                    int x = points[k][0];
                    int y = points[k][1];

                    if (
                            x >= Math.min(left[0], right[0]) && x <= Math.max(left[0], right[0])
                            && y >= Math.min(left[1], right[1]) && y <= Math.max(left[1], right[1])
                    ) {
                        isValidPair = false;
                        break;
                    }
                }

                if (isValidPair) res++;
            }
        }

        return res;
    }
}
