package leetcode.array.prefixSum;

public class M_3070_CountSubmatricesWithTopLeftElementAndSumLessThanK {

    /**
     * Prefix Sum, space optimized
     * ----------
     * Idea: use O(n) space to store the prefix sum of each column
     * ----------
     * TC: O(m*n)
     * SC: O(n)
     */
    public int countSubmatrices1(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        int[] prefSum = new int[cols];
        int res = 0;

        for (int i = 0; i < rows; ++i) {
            int rowSum = 0;

            for (int j = 0; j < cols; ++j) {
                prefSum[j] += grid[i][j];
                rowSum += prefSum[j];

                if (rowSum <= k) res++;
            }
        }

        return res;
    }

    /**
     * Prefix Sum, intuitive
     * ----------
     * Idea: use O(m*n) space to store the prefix sum of each cell
     * ----------
     * TC: O(m*n)
     * SC: O(m*n)
     */
    public int countSubmatrices(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        int[][] prefSum = new int[rows][cols];
        int res = 0;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                prefSum[i][j] += grid[i][j];
                if (i > 0) prefSum[i][j] += prefSum[i - 1][j];
                if (j > 0) prefSum[i][j] += prefSum[i][j - 1];
                if (i > 0 && j > 0) prefSum[i][j] -= prefSum[i - 1][j - 1];

                if (prefSum[i][j] <= k) res++;
            }
        }

        return res;
    }
}
