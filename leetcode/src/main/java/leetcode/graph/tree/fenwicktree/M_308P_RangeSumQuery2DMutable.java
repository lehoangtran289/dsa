package leetcode.graph.tree.fenwicktree;

public class M_308P_RangeSumQuery2DMutable {

    private final BIT bit;
    private final int[][] matrix;

    public M_308P_RangeSumQuery2DMutable(int[][] matrix) {
        this.bit = new BIT(matrix.length, matrix[0].length);
        this.matrix = matrix;

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                bit.update(i + 1, j + 1, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        bit.update(row + 1, col + 1, val - matrix[row][col]);
        matrix[row][col] = val;
    }

    // inclusion / exclusion
    // O(logMN)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return bit.get(row2 + 1, col2 + 1)
               - bit.get(row1, col2 + 1)
               - bit.get(row2 + 1, col1)
               + bit.get(row1, col1);
    }

    static class BIT {
        int[][] bit;
        int rows;
        int cols;

        BIT(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.bit = new int[rows + 1][cols + 1];
        }

        // O(logMN)
        void update(int x, int y, int v) {
            for (int i = x; i <= rows; i += (i & -i)) {
                for (int j = y; j <= cols; j += (j & -j)) {
                    bit[i][j] += v;
                }
            }
        }

        // O(logMN)
        int get(int x, int y) {
            int res = 0;
            for (int i = x; i >= 1; i &= (i - 1)) {
                for (int j = y; j >= 1; j &= (j - 1)) {
                    res += bit[i][j];
                }
            }
            return res;
        }
    }
}
