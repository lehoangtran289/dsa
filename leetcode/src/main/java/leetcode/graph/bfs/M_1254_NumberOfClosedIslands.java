package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_1254_NumberOfClosedIslands {

    private static final int[][] DIRS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // U, R, D, L

    /**
     * TC: O(rows * cols)
     * SC: O(rows * cols)
     */
    public int closedIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int res = 0;

        // init bfs states
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 1; i < rows - 1; ++i) {
            for (int j = 1; j < cols - 1; ++j) {
                if (grid[i][j] == 1 || visited[i][j]) continue;
                if (bfs(grid, visited, i, j)) res++;
            }
        }
        return res;
    }

    private boolean bfs(int[][] grid, boolean[][] visited, int startX, int startY) {
        int rows = grid.length, cols = grid[0].length;
        boolean result = true;

        // bfs states
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        // bfs
        while (!queue.isEmpty()) {
            int[] curCell = queue.poll();
            int curX = curCell[0], curY = curCell[1];

            if (isBorderCell(curX, curY, rows, cols)) result = false;

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (
                        isValidCell(nextX, nextY, rows, cols)
                        && !visited[nextX][nextY]
                        && grid[nextX][nextY] != 1
                ) {
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        return result;
    }

    private boolean isBorderCell(int x, int y, int rows, int cols) {
        return x == 0 || x == rows - 1 || y == 0 || y == cols - 1;
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
