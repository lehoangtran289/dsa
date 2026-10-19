package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_1730P_ShortestPathToGetFood {

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U,D,L,R

    /**
     * TC: O(rows * cols)
     * SC: O(rows * cols)
     */
    public int getFood(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;

        // find starting point
        int startX = 0, startY = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '*') {
                    startX = i;
                    startY = j;
                }
            }
        }

        // init BFS states
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.add(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] curPosition = queue.poll();
            int x = curPosition[0], y = curPosition[1], dist = curPosition[2];

            if (grid[x][y] == '#') return dist;

            for (int[] dir : DIRS) {
                int nx = x + dir[0], ny = y + dir[1];

                if (
                        isValidCell(nx, ny, rows, cols)
                        && !visited[nx][ny]
                        && grid[nx][ny] != 'X'
                ) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return -1;
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
