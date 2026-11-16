package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_1102P_PathWithMaximumMinimumValue {

    private final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // L, R, U, D

    /**
     * Idea: maximum minimum -> binary search on results
     * ~ Check if there exists a path having cell values >= target -> BFS
     * ---
     * TC: O(logN * rows * cols), N = max values in grid
     * SC: O(rows * cols)
     */
    public int maximumMinimumPath(int[][] grid) {
        int res = 0;

        int l = 0, r = 1 << 30;
        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (isValid(grid, mid)) {
                res = Math.max(res, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    /**
     * Check if there is a path having cell values >= target
     * -> Path score at least = target
     */
    private boolean isValid(int[][] grid, int target) {
        // base case
        if (grid[0][0] < target) return false;

        final int rows = grid.length, cols = grid[0].length;

        // BFS states: [curX, curY]
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.add(new int[]{0, 0});

        // process BFS
        while (!queue.isEmpty()) {
            int[] curCell = queue.poll();
            int curX = curCell[0], curY = curCell[1];

            if (curX == rows - 1 && curY == cols - 1) return true;

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (
                        isCellValid(nextX, nextY, rows, cols)
                        && !visited[nextX][nextY]
                        && grid[nextX][nextY] >= target
                ) {
                    queue.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }

        return false;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
