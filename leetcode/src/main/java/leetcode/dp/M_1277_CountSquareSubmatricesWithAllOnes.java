package leetcode.dp;

import java.util.Arrays;

public class M_1277_CountSquareSubmatricesWithAllOnes {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        System.out.println(new M_1277_CountSquareSubmatricesWithAllOnes().countSquares(matrix));
    }

    // count number of squares at a specific cells and save to dp
    private int solve(int i, int j, int[][] matrix, int[][] dp) {
        if (i >= matrix.length || j >= matrix[0].length) return 0;
        if (matrix[i][j] == 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int right = solve(i, j + 1, matrix, dp);
        int below = solve(i + 1, j, matrix, dp);
        int diag = solve(i + 1, j + 1, matrix, dp);
        dp[i][j] = 1 + Math.min(right, Math.min(below, diag));

        return dp[i][j];
    }

    public int countSquares(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                dp[i][j] = -1;
            }
        }

        int count = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                count += solve(i, j, matrix, dp); // number of ones's square at this cell
            }
        }
        System.out.println(Arrays.deepToString(dp));

        return count;
    }
}
