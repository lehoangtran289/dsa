package leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class M_3623_CountNumberOfTrapezoidsI {

    private final int MOD = (int) 1e9 + 7;

    /**
     * Math + Combinatorics
     * --------------------------
     * Observation:
     * To form a trapezoid, we need two pairs of points with the same y-coordinate.
     * For each unique y-coordinate, we can calculate the number of ways to choose
     * 2 points from the points that share that y-coordinate. This is given by the
     * combination formula C(n, 2) = n * (n - 1) / 2, where n is the number of points
     * with that y-coordinate.
     */
    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> pointNum = new HashMap<>();

        for (int[] point : points) {
            pointNum.put(point[1], pointNum.getOrDefault(point[1], 0) + 1);
        }

        long res = 0;
        long totalEdges = 0;

        for (int num : pointNum.values()) {
            long curEdges = ((long) num * (num - 1)) / 2;
            res = (res + curEdges * totalEdges) % MOD;
            totalEdges = (totalEdges + curEdges) % MOD;
        }

        return (int) res;
    }
}
