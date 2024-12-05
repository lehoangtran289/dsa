package leetcode.dp;

import java.util.Arrays;

/**
 * Top down dp
 */
public class M_2684_MaximumNumberOfMovesInAGrid {
    public static int[] dirs = {-1, 0, 1};

    public static void main(String[] args) {
        int[][] grid = {{2, 4, 3, 5}, {5, 4, 9, 3}, {3, 4, 2, 11}, {10, 9, 13, 15}};
        System.out.println(new M_2684_MaximumNumberOfMovesInAGrid().maxMoves(grid));
    }

    public int maxMoves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        int result = 0;
        for (int i = 0; i < rows; ++i) {
            result = Math.max(result, dp(i, 0, grid, dp));
        }

        return result;
    }

    private int dp(int r, int c, int[][] grid, int[][] dp) {
        if (dp[r][c] != -1) return dp[r][c];

        int curMax = 0;
        for (int dir : dirs) {
            if (
                    r + dir >= 0 &&
                    r + dir < grid.length &&
                    c + 1 < grid[0].length &&
                    grid[r + dir][c + 1] > grid[r][c]
            ) {
                curMax = Math.max(curMax, 1 + dp(r + dir, c + 1, grid, dp));
            }
        }

        dp[r][c] = curMax;
        return curMax;
    }
}
