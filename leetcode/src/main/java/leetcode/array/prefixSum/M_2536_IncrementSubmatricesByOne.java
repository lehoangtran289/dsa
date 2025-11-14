package leetcode.array.prefixSum;

import java.util.Arrays;

public class M_2536_IncrementSubmatricesByOne {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(
                rangeAddQueries(3, new int[][]{
                        {0, 0, 1, 1},
                        {1, 1, 2, 2},
                        {0, 2, 2, 2}
                })
        )); // [[1, 1, 1], [1, 2, 2], [0, 1, 2]]
    }

    /**
     * Prefix Sum + Difference Array for 2D array
     * --------------------------------
     * TC: O(n * (m + n)), m = queries.length
     * SC: O(n^2)
     * --------------------------------
     */
    public static int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] res = new int[n][n];

        for (int[] q : queries) {
            int x1 = q[0], y1 = q[1];
            int x2 = q[2], y2 = q[3];

            for (int i = x1; i <= x2; ++i) {
                res[i][y1]++;
                if (y2 + 1 < n) res[i][y2 + 1]--;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                res[i][j] += res[i][j - 1];
            }
        }

        return res;
    }
}
