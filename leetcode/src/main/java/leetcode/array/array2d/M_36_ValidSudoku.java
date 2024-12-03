package leetcode.array.array2d;

import java.util.HashSet;
import java.util.Set;

public class M_36_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        // validate rows
        for (int i = 0; i < board.length; i ++) {
            Set<Character> row = new HashSet<>();
            for (int j = 0; j < board[0].length; j ++) {
                char cell = board[i][j];
                if (cell != '.') {
                    if (row.contains(cell)) return false;
                    row.add(cell);
                }
            }
        }

        // validate cols
        for (int j = 0; j < board[0].length; j ++) {
            Set<Character> col = new HashSet<>();
            for (int i = 0; i < board.length; i ++) {
                char cell = board[i][j];
                if (cell != '.') {
                    if (col.contains(cell)) return false;
                    col.add(cell);
                }
            }
        }

        // validate boxes
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[0].length; j += 3) {

                // validate box
                Set<Character> box = new HashSet<>();
                for (int r = 0; r < 3; ++r) {
                    for (int c = 0; c < 3; ++c) {
                        char cell = board[i + r][j + c];
                        if (cell != '.') {
                            if (box.contains(cell)) return false;
                            box.add(cell);
                        }
                    }
                }
            }
        }

        return true;
    }
}
