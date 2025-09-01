package leetcode.backtrack;

public class H_37_SodukuSolver {

    private char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        backtrack(0, 0);
    }

    private boolean backtrack(int r, int c) {
        // base cases
        if (c == 9) { // end of row
            r++;
            c = 0;
        }
        if (r == 9) return true; // reach bottom right -> soduku solved
        if (board[r][c] != '.') return backtrack(r, c + 1);

        for (int num = 1; num <= 9; ++num) {
            // choose only valid num
            if (!isValidBoard(r, c, num)) continue;

            // set
            board[r][c] = (char) (num + '0');

            // backtrack
            if (backtrack(r, c + 1)) return true;

            // undo
            board[r][c] = '.';
        }

        return false;
    }

    private boolean isValidBoard(int r, int c, int num) {
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
