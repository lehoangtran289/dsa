package leetcode.array.array2d;

public class M_36_ValidSudoku {

    /**
     * Simulation
     * -------------
     * Time: O(1) since board size (9x9), if board size n x n, then O(n^2)
     * Space: O(1) since board size (9x9)
     */
    public boolean isValidSudoku(char[][] board) {
        // validate rows and columns
        for (int i = 0; i < 9; ++i) {
            int[] row = new int[10];
            int[] col = new int[10];

            for (int j = 0; j < 9; ++j) {
                char rowCell = board[i][j];
                char colCell = board[j][i];

                if (rowCell != '.') {
                    row[rowCell - '0']++;
                    if (row[rowCell - '0'] > 1) return false;
                }

                if (colCell != '.') {
                    col[colCell - '0']++;
                    if (col[colCell - '0'] > 1) return false;
                }
            }
        }

        // validate boxes
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                int[] box = new int[10];

                // filling box elements
                for (int r = 0; r < 3; ++r) {
                    for (int c = 0; c < 3; ++c) {
                        char val = board[i + r][j + c];
                        if (val == '.') continue;

                        box[val - '0']++;
                        if (box[val - '0'] > 1) return false;
                    }
                }
            }
        }

        return true;
    }
}
