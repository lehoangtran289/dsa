package leetcode.array.array2d;

import java.util.*;

public class M_3446_SortMatrixByDiagonals {
    public static void main(String[] args) {
        M_3446_SortMatrixByDiagonals obj = new M_3446_SortMatrixByDiagonals();
        System.out.println(Arrays.deepToString(obj.sortMatrix(new int[][]
                {{1,7,3},{9,8,2},{4,5,6}}
        ))); //  [[8,2,3],[9,6,7],[4,5,1]]
    }

    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n][n];

        // traverse rows to get bottom left diagonals
        for (int i = 0; i < n; ++i) {
            List<Integer> diagonal = new ArrayList<>();

            for (int x = i, y = 0; x < n && y < n; x++, y++) {
                diagonal.add(grid[x][y]);
            }

            Collections.sort(diagonal, (a, b) -> b - a);

            // add sorted diagonal to res array
            for (int x = i, y = 0; x < n && y < n; x++, y++) {
                res[x][y] = diagonal.get(y);
            }
        }

        // traverse cols to get top right diagonals
        for (int i = 1; i < n; ++i) {
            List<Integer> diagonal = new ArrayList<>();

            for (int x = 0, y = i; x < n && y < n; x++, y++) {
                diagonal.add(grid[x][y]);
            }

            Collections.sort(diagonal);

            // add sorted diagonal to res array
            for (int x = 0, y = i; x < n && y < n; x++, y++) {
                res[x][y] = diagonal.get(x);
            }
        }

        return res;
    }
}
