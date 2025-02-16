package leetcode.backtrack;

import java.util.HashSet;
import java.util.Set;

public class H_52_TotalNQueens {
    public int totalNQueens(int n) {
        return backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), n);
    }

    // count solutions
    private int backtrack(
            int row,
            Set<Integer> cols,
            Set<Integer> diags,
            Set<Integer> antiDiags,
            int size
    ) {
        if (row == size) return 1;

        int solutions = 0;
        for (int col = 0; col < size; ++col) {
            int curDiag = row - col;
            int curAntiDiag = row + col;

            // If not placeable -> continue
            if (
                    cols.contains(col) ||
                    diags.contains(curDiag) ||
                    antiDiags.contains(curAntiDiag)
            ) {
                continue;
            }

            // process
            cols.add(col);
            diags.add(curDiag);
            antiDiags.add(curAntiDiag);

            // backtrack
            solutions += backtrack(row + 1, cols, diags, antiDiags, size);

            // undo
            cols.remove(col);
            diags.remove(curDiag);
            antiDiags.remove(curAntiDiag);
        }

        return solutions;
    }
}
