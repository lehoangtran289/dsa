package leetcode.array.prefixSum;

public class M_1895_LargestMagicSquare {

    /**
     * Prefix sum
     * ----
     * TC: O(m * n * min(m, n))
     * SC: O(m * n) - for the prefix sum arrays
     */
    public int largestMagicSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // prefix sum of each row
        int[][] rowSum = new int[rows][cols];
        for (int i = 0; i < rows; ++i) {
            rowSum[i][0] = grid[i][0];

            for (int j = 1; j < cols; ++j) {
                rowSum[i][j] = rowSum[i][j - 1] + grid[i][j];
            }
        }

        // prefix sum of each column
        int[][] colSum = new int[rows][cols];
        for (int j = 0; j < cols; ++j) {
            colSum[0][j] = grid[0][j];

            for (int i = 1; i < rows; ++i) {
                colSum[i][j] = colSum[i - 1][j] + grid[i][j];
            }
        }

        for (int edge = Math.min(rows, cols); edge >= 2; --edge) {
            for (int i = 0; i <= rows - edge; ++i) {
                for (int j = 0; j <= cols - edge; ++j) {
                    int stdSum = rowSum[i][j + edge - 1] - (j > 0 ? rowSum[i][j - 1] : 0);

                    boolean isMagicSquare = true;

                    // validate row sum
                    for (int row = i + 1; row < i + edge; ++row) {
                        int curRowSum = rowSum[row][j + edge - 1] - (j > 0 ? rowSum[row][j - 1] : 0);

                        if (curRowSum != stdSum) {
                            isMagicSquare = false;
                            break;
                        }
                    }
                    if (!isMagicSquare) continue;

                    // validate col sum
                    for (int col = j; col < j + edge; ++col) {
                        int curColSum = colSum[i + edge - 1][col] - (i > 0 ? colSum[i - 1][col] : 0);

                        if (curColSum != stdSum) {
                            isMagicSquare = false;
                            break;
                        }
                    }
                    if (!isMagicSquare) continue;

                    // validate 2 diags
                    int diagLeftSum = 0, diagRightSum = 0;
                    for (int k = 0; k < edge; ++k) {
                        diagLeftSum += grid[i + k][j + k];
                        diagRightSum += grid[i + k][j + edge - 1 - k];
                    }

                    if (diagLeftSum == stdSum && diagRightSum == stdSum) return edge;
                }
            }
        }

        return 1;
    }
}
