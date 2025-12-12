package leetcode.array;

import java.util.Arrays;

public class M_3531_CountCoveredBuildings {
    public static void main(String[] args) {
        System.out.println(countCoveredBuildings(5, new int[][]{
                {1, 3},
                {3, 2},
                {3, 3},
                {3, 5},
                {5, 3}
        })); // 1
    }

    /**
     * Simulation
     * ----------------------------------
     * TC: O(m - number of buildings
     * SC: O(n) - arrays to track min/max rows/cols
     */
    public static int countCoveredBuildings(int n, int[][] buildings) {
        int[] maxRow = new int[n + 1];
        int[] minRow = new int[n + 1];
        int[] maxCol = new int[n + 1];
        int[] minCol = new int[n + 1];

        Arrays.fill(minRow, 1 << 30);
        Arrays.fill(minCol, 1 << 30);

        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            maxRow[y] = Math.max(maxRow[y], x);
            minRow[y] = Math.min(minRow[y], x);
            maxCol[x] = Math.max(maxCol[x], y);
            minCol[x] = Math.min(minCol[x], y);
        }

        int res = 0;

        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            if (x > minRow[y] && x < maxRow[y] && y > minCol[x] && y < maxCol[x]) {
                res++;
            }
        }

        return res;
    }
}
