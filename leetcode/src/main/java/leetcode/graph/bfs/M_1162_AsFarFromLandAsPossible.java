package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_1162_AsFarFromLandAsPossible {

    private static final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // L, R, U, D

    /**
     * Multi-source BFS
     * ---
     * TC: O(n * m)
     * SC: O(n * m)
     */
    public int maxDistance(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        // init multi-source
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1) { // land
                    queue.add(new int[]{i, j});
                    dist[i][j] = 0;
                } else { // water
                    dist[i][j] = 1 << 30;
                }
            }
        }

        // BFS traverse, a cell is visited first time = shortest distance to land
        int res = -1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : DIRS) {
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];

                if (
                        isCellValid(nextX, nextY, rows, cols)
                        && dist[nextX][nextY] == 1 << 30
                ) {
                    queue.add(new int[]{nextX, nextY});
                    dist[nextX][nextY] = dist[cur[0]][cur[1]] + 1;

                    res = Math.max(res, dist[nextX][nextY]);
                }
            }
        }

        return res;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
