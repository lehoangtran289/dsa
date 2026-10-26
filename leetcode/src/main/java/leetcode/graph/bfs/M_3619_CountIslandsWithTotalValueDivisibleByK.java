package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_3619_CountIslandsWithTotalValueDivisibleByK {

    private static final int[][] DIRS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // U, R, D, L

    /**
     * TC: O(rows * cols)
     * SC: O(rows * cols)
     */
    public int countIslands(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        int res = 0;

        // init bfs states
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 0 || visited[i][j]) continue;
                if (bfs(grid, visited, i, j) % k == 0) res++;
            }
        }
        return res;
    }

    private long bfs(int[][] grid, boolean[][] visited, int startX, int startY) {
        int rows = grid.length, cols = grid[0].length;

        // bfs states
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        long islandSum = grid[startX][startY];

        // bfs
        while (!queue.isEmpty()) {
            int[] curCell = queue.poll();
            int curX = curCell[0], curY = curCell[1];

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (
                        isValidCell(nextX, nextY, rows, cols)
                        && !visited[nextX][nextY]
                        && grid[nextX][nextY] != 0
                ) {
                    visited[nextX][nextY] = true;
                    islandSum += grid[nextX][nextY];
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        return islandSum;
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
