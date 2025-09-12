package leetcode.dp;

import java.util.*;

public class M_64_MinimumPathSum {

    /**
     * Idea: Bottom up DP
     * dp[i][j] = grid[i][j] + min(dp[i+1][j], dp[i][j+1])
     * Base case: dp[n-1][m-1] = grid[n-1][m-1]
     * -----------------------
     * Time: O(n*m)
     * Space: O(n*m)
     */
    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];

        dp[n - 1][m - 1] = grid[n - 1][m - 1];

        // last row
        for (int j = m - 2; j >= 0; --j) {
            dp[n - 1][j] = dp[n - 1][j + 1] + grid[n - 1][j];
        }

        // last col
        for (int i = n - 2; i >= 0; --i) {
            dp[i][m - 1] = dp[i + 1][m - 1] + grid[i][m - 1];
        }

        // dp bottom up
        for (int i = n - 2; i >= 0; --i) {
            for (int j = m - 2; j >= 0; --j) {
                dp[i][j] = grid[i][j] + Math.min(
                        dp[i][j + 1], dp[i + 1][j]
                );
            }
        }

        return dp[0][0];
    }

    static class Cell {
        int x;
        int y;
        int w;

        public Cell(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    /**
     * Idea: Dijkstra
     */
    public int minPathSum2(int[][] grid) {
        final int[][] DIRS = new int[][]{{1, 0}, {0, 1}};
        int rows = grid.length, cols = grid[0].length;

        // init dijkstra params
        int[][] dist = new int[rows][cols];
        Queue<Cell> pq = new PriorityQueue<>((a, b) -> a.w - b.w);

        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
        dist[0][0] = grid[0][0];
        pq.add(new Cell(0, 0, grid[0][0]));

        // Dijkstra
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();

            for (int[] d : DIRS) {
                int nextX = cur.x + d[0];
                int nextY = cur.y + d[1];

                if (isValidCell(nextX, nextY, rows, cols)) {
                    int nextW = cur.w + grid[nextX][nextY];

                    if (nextW < dist[nextX][nextY]) {
                        dist[nextX][nextY] = nextW;
                        pq.add(new Cell(nextX, nextY, nextW));
                    }
                }
            }
        }

        return dist[rows - 1][cols - 1];
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
