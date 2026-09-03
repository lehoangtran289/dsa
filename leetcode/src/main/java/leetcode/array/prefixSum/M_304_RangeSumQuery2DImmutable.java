package leetcode.array.prefixSum;

public class M_304_RangeSumQuery2DImmutable {

    private final int[][] prefixSum;

    /**
     * Idea: Prefix Sum + inclusion / exclusion
     * ---
     * TC: O(rows * cols) for constructor, O(1) for sumRegion
     * SC: O(rows * cols)
     */
    public M_304_RangeSumQuery2DImmutable(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        this.prefixSum = new int[rows + 1][cols + 1];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                prefixSum[i + 1][j + 1] = prefixSum[i][j + 1] // horizontal
                                          + prefixSum[i + 1][j] // vertical
                                          - prefixSum[i][j] // diagonal
                                          + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum[row2 + 1][col2 + 1]
               - prefixSum[row2 + 1][col1] // horizontal
               - prefixSum[row1][col2 + 1] // vertical
               + prefixSum[row1][col1]; // diagonal
    }
}
