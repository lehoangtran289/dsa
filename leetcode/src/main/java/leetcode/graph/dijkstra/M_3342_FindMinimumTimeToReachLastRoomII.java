package leetcode.graph.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M_3342_FindMinimumTimeToReachLastRoomII {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U, D, L, R

    /**
     * ---------------------------------------------------------------
     * Dijkstra's algorithm
     * ---------------------------------------------------------------
     * TC: O(n * m * log(n * m))
     * SC: O(n * m)
     */
    public int minTimeToReach(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] dist = new int[n][m];
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
        dist[0][0] = 0;

        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
        pq.add(new Cell(0, 0, 1));

        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            int curTime = dist[cur.x][cur.y];

            if (cur.x == n - 1 && cur.y == m - 1)
                return dist[cur.x][cur.y];

            for (int[] d : DIRS) {
                int nextX = cur.x + d[0];
                int nextY = cur.y + d[1];

                if (!isValidCell(n, m, nextX, nextY)) continue;

                int nextTime = Math.max(curTime, grid[nextX][nextY]) + cur.step;
                if (nextTime < dist[nextX][nextY]) {
                    dist[nextX][nextY] = nextTime;
                    pq.add(new Cell(nextX, nextY, cur.step == 1 ? 2 : 1));
                }
            }
        }

        return dist[n - 1][m - 1];
    }

    private boolean isValidCell(int n, int m, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static class Cell {
        int x;
        int y;
        int step;

        Cell(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}
