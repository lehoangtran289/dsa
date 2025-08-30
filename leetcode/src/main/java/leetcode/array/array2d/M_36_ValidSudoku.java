package leetcode.array.array2d;

public class M_36_ValidSudoku {

    /**
     * Simulation
     * -------------
     * Time: O(1) since board size (9x9), if board size n x n, then O(n^2)
     * Space: O(1) since board size (9x9)
     */
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        int[][] rows = new int[N][N];
        int[][] cols = new int[N][N];

        // validate rows and columns
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                char val = board[i][j];
                if (val == '.') continue;

                rows[i][val - '1']++;
                if (rows[i][val - '1'] > 1) return false;

                cols[val - '1'][j]++;
                if (cols[val - '1'][j] > 1) return false;
            }
        }

        // validate boxes
        for (int i = 0; i < N; i += 3) {
            for (int j = 0; j < N; j += 3) {
                int[] box = new int[N];

                // filling box elements
                for (int r = 0; r < 3; ++r) {
                    for (int c = 0; c < 3; ++c) {
                        char val = board[i + r][j + c];
                        if (val == '.') continue;

                        box[val - '1']++;
                        if (box[val - '1'] > 1) return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Bit masking
     */
    public boolean isValidSudoku2(char[][] board) {
        int N = 9;

        int[] rows = new int[N];
        int[] cols = new int[N];

        // validate rows and columns
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (board[i][j] == '.') continue;
                int bitPos = 1 << (board[i][j] - '1');

                if ((rows[i] & bitPos) > 0) return false;
                rows[i] |= bitPos;

                if ((cols[j] & bitPos) > 0) return false;
                cols[j] |= bitPos;
            }
        }

        // validate boxes
        // 0 1 2
        // 3 4 5
        // 6 7 8
        int[] boxes = new int[N];

        for (int i = 0; i < N; i += 3) {
            for (int j = 0; j < N; j += 3) {
                int boxId = i / 3 * 3 + j / 3;

                // filling box elements
                for (int r = 0; r < 3; ++r) {
                    for (int c = 0; c < 3; ++c) {
                        char val = board[i + r][j + c];
                        if (val == '.') continue;
                        int bitPos = 1 << (val - '1');

                        if ((boxes[boxId] & bitPos) > 0) return false;
                        boxes[boxId] |= bitPos;
                    }
                }
            }
        }

        return true;
    }
}
