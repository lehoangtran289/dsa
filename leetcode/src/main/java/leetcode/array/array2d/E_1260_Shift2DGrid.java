package leetcode.array.array2d;

import java.util.ArrayList;
import java.util.List;

public class E_1260_Shift2DGrid {
    static void main() {
        System.out.println(shiftGrid(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }, 1)); // Output: [[9,1,2],[3,4,5],[6,7,8]]
    }

    /**
     * Modulo
     * Idea: calculate the new position of each element after k shifts
     * - First, calculate new column by (j + k) % cols
     * - Next, calculate how many rows increased by (j + k) / cols
     * - Finally, calculate new row by (i + xIncrement) % rows
     * ---
     * TC: O(m * n),
     * SC: O(m * n)
     */
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();

        int[][] newGrid = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int newY = (j + k) % cols;
                int xIncrement = (j + k) / cols;
                int newX = (i + xIncrement) % rows;

                newGrid[newX][newY] = grid[i][j];
            }
        }


        for (int[] row : newGrid) {
            List<Integer> newRow = new ArrayList<>();
            for (int num : row) newRow.add(num);
            res.add(newRow);
        }

        return res;
    }
}
