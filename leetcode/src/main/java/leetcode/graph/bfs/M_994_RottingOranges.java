package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_994_RottingOranges {

    private static final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // L, R, U, D

    /**
     * Multi-source BFS
     * ---
     * TC: O(n * m)
     * SC: O(n * m)
     */
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;

        int totalFreshes = 0;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }

                if (grid[i][j] == 1)
                    totalFreshes++;
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isProcessed = false;

            // Process all adj cells in the current level
            while (size-- > 0) {
                int[] cur = queue.poll();

                for (int[] dir : DIRS) {
                    int nextX = cur[0] + dir[0];
                    int nextY = cur[1] + dir[1];

                    if (
                            isCellValid(nextX, nextY, rows, cols)
                            && !visited[nextX][nextY]
                            && grid[nextX][nextY] == 1
                    ) {
                        visited[nextX][nextY] = true;
                        totalFreshes--;
                        isProcessed = true;

                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }
            if (isProcessed) res++;
        }

        return totalFreshes == 0 ? res : -1;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
