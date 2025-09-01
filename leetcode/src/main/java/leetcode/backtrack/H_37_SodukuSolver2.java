package leetcode.backtrack;

public class H_37_SodukuSolver2 {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    /**
     * Idea: find the first empty cell, try all possible numbers (1-9) that can be placed in that cell,
     * and recursively attempt to solve the rest of the board.
     * If a number leads to a solution, return true.
     * If no number works, backtrack by resetting the cell and trying the next number.
     */
    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') continue;

                for (int num = 1; num <= 9; ++num) {
                    // choose only valid num
                    if (!isValidBoard(board, i, j, num)) continue;

                    // set
                    board[i][j] = (char) (num + '0');

                    // backtrack, if true -> soduku solved
                    if (backtrack(board)) return true;

                    // undo
                    board[i][j] = '.';
                }
                // no valid number found -> need to backtrack
                return false;
            }
        }
        // all cells are filled -> soduku solved
        return true;
    }

    private boolean isValidBoard(char[][] board, int r, int c, int num) {
        char numChar = (char) (num + '0');

        for (int i = 0; i < 9; ++i) {
            if (board[r][i] == numChar) return false;
            if (board[i][c] == numChar) return false;
        }

        int boxRow = r / 3;
        int boxCol = c / 3;

        for (int i = boxRow * 3; i < (boxRow + 1) * 3; ++i) {
            for (int j = boxCol * 3; j < (boxCol + 1) * 3; ++j) {
                if (board[i][j] == numChar) return false;
            }
        }

        return true;
    }
}
