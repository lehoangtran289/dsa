package leetcode.array.array2d;

import java.util.HashMap;
import java.util.Map;

public class M_2661_FirstCompletelyPaintedRowOrColumn {
    public static void main(String[] args) {
        System.out.println(firstCompleteIndex(new int[]{1, 4, 5, 2, 6, 3}, new int[][]{{4, 3, 5}, {1, 2, 6}}));
    }

    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        Map<Integer, int[]> idxMap = new HashMap<>(); // value -> [row, col]
        Map<Integer, Integer> rowMap = new HashMap<>(); // row -> #cols
        Map<Integer, Integer> colMap = new HashMap<>(); // col -> #rows

        for (int i = 0; i < rows; ++i) {
            rowMap.put(i, cols);
        }

        for (int i = 0; i < cols; ++i) {
            colMap.put(i, rows);
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int[] idx = new int[]{i, j};
                idxMap.put(mat[i][j], idx);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int[] idx = idxMap.get(arr[i]);
            if (rowMap.get(idx[0]) - 1 == 0 || colMap.get(idx[1]) - 1 == 0) return i;

            rowMap.put(idx[0], rowMap.get(idx[0]) - 1);
            colMap.put(idx[1], colMap.get(idx[1]) - 1);
        }

        return -1;
    }
}
