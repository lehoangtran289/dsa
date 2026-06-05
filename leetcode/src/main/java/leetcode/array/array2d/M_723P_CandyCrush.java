package leetcode.array.array2d;

public class M_723P_CandyCrush {
    /**
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 110 0 0 0 114
     * 210 0 0 0 214
     * 310 0 0 113 314
     * 410 0 0 213 414
     * 610 211 112 313 614
     * 710 311 412 613 714
     * 810 411 512 713 1014
     */
    public static void main(String[] args) {
        int[][] board = {
                {110, 5, 112, 113, 114},
                {210, 211, 5, 213, 214},
                {310, 311, 3, 313, 314},
                {410, 411, 412, 5, 414},
                {5, 1, 512, 3, 3},
                {610, 4, 1, 613, 614},
                {710, 1, 2, 713, 714},
                {810, 1, 2, 1, 1},
                {1, 1, 2, 2, 2},
                {4, 1, 4, 4, 1014}
        };
        int[][] res = candyCrush(board);
        for (int[] row : res) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    /**
     * TC: O((mn)^2) worst case when we crush 1 cell each time, we need to do m*n times to crush all cells
     * SC: O(mn) for crushedBoard
     */
    public static int[][] candyCrush(int[][] board) {
        int rows = board.length, cols = board[0].length;

        while (true) {
            boolean isCrushed = false;
            boolean[][] crushedBoard = new boolean[rows][cols];

            // find crushed cells
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols - 2; ++j) {
                    if (board[i][j] != 0 && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2]) {
                        isCrushed = true;
                        crushedBoard[i][j] = true;
                        crushedBoard[i][j + 1] = true;
                        crushedBoard[i][j + 2] = true;
                    }
                }
            }

            for (int j = 0; j < cols; ++j) {
                for (int i = 0; i < rows - 2; ++i) {
                    if (board[i][j] != 0 && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j]) {
                        isCrushed = true;
                        crushedBoard[i][j] = true;
                        crushedBoard[i + 1][j] = true;
                        crushedBoard[i + 2][j] = true;
                    }
                }
            }

            // drop
            for (int j = 0; j < cols; ++j) {
                // find drop bottom line
                int p1 = rows - 1;
                while (p1 >= 0) {
                    if (crushedBoard[p1][j]) break;
                    p1--;
                }
                if (p1 < 0) continue;

                // find cells to start droping
                int p2 = p1 - 1;
                while (p2 >= 0) {
                    if (!crushedBoard[p2][j]) {
                        board[p1][j] = board[p2][j];
                        p1--;
                    }
                    p2--;
                }

                // new cells from top = 0;
                while (p1 >= 0) {
                    board[p1][j] = 0;
                    p1--;
                }
            }

            if (!isCrushed) break;
        }

        return board;
    }
}
