package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_934_ShortestBridge {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * DFS + BFS
     * ---
     * TC: O(n^2) - O(V + E) = number of cells = O(n^2)
     * SC: O(n^2)
     */
    public int shortestBridge(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] island = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        // step 1: find a '1' cell, DFS to mark the first island
        outer:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    dfsMark(grid, island, i, j, rows, cols);
                    break outer;
                }
            }
        }

        // seed the BFS queue with all cells of the first island
        int[][] dist = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (island[i][j]) {
                    queue.add(new int[]{i, j});
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = 1 << 30;
                }
            }
        }

        // step 2: multi-source BFS, expanding outward from island1
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int[] dir : DIRS) {
                int nx = x + dir[0], ny = y + dir[1];

                if (
                        isValid(nx, ny, rows, cols)
                        && dist[nx][ny] == 1 << 30
                ) {
                    if (grid[nx][ny] == 1) return dist[x][y]; // reach island 2

                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return -1; // unreachable
    }

    private void dfsMark(int[][] grid, boolean[][] island, int x, int y, int rows, int cols) {
        if (!isValid(x, y, rows, cols) || grid[x][y] != 1 || island[x][y])
            return;

        island[x][y] = true;
        for (int[] dir : DIRS) {
            dfsMark(grid, island, x + dir[0], y + dir[1], rows, cols);
        }
    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
