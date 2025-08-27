package leetcode.dp;

import java.util.*;

/**
 * DP with memoization + DFS
 * -----------------
 * Let m,n be the number of rows and columns of the given matrix grid.
 * - Time complexity: O(m⋅n)
 *      + There are O(m⋅n) substates in the memoization search, and each state takes O(1) time to compute.
 * - Space complexity: O(mn).
 *      + Both the memoization table and the recursion stack require O(m⋅n) space.
 */
public class H_3459_LengthOfLongestVShapedDiagonalSegment {
    private static final int[][] DIRS = new int[][]{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    private int[][][][] memo; // [x, y, direction, has turned yet]
    private int[][] grid;
    private int rows, cols;

    public int lenOfVDiagonal(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.memo = new int[rows][cols][4][2];
        int res = 0;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                for (int k = 0; k < 4; ++k) {
                    Arrays.fill(memo[i][j][k], -1);
                }
            }
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1) {
                    // try all 4 directions
                    for (int dir = 0; dir < 4; ++dir) {
                        res = Math.max(res, dfs(i, j, dir, 0, 2) + 1);
                    }
                }
            }
        }

        return res;
    }

    private int dfs(
            int curX, // current position
            int curY, // current position
            int dir, // current direction
            int isTurn, // 0 = false or 1 = true
            int target // next target value (0 or 2)
    ) {
        int nextX = curX + DIRS[dir][0];
        int nextY = curY + DIRS[dir][1];

        // base cases
        if (!isValidCell(nextX, nextY) || grid[nextX][nextY] != target) return 0;
        if (memo[nextX][nextY][dir][isTurn] != -1) return memo[nextX][nextY][dir][isTurn];

        // dfs next directions
        // try get path after turn 90 degree clockwise
        int nextTarget = getNextTarget(target);
        int steps = dfs(nextX, nextY, dir, isTurn, nextTarget);
        int stepsIfRotate = isTurn == 0 ? dfs(nextX, nextY, getNextDir(dir), 1, nextTarget) : 0;

        return memo[nextX][nextY][dir][isTurn] = Math.max(steps, stepsIfRotate) + 1;
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private int getNextTarget(int curTarget) {
        return curTarget == 2 ? 0 : 2;
    }

    private int getNextDir(int curDir) {
        return (curDir + 1) % 4;
    }
}
