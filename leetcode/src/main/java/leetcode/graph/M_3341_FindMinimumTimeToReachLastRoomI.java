package leetcode.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M_3341_FindMinimumTimeToReachLastRoomI {
    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final int[][] DIRS = new int[][]{{-1, 0},{1, 0}, {0, -1}, {0, 1}}; // U, D, L, R

    public int minTimeToReach(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dist = new int[n][m];
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
        dist[0][0] = 0;

        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> dist[a.x][a.y] - dist[b.x][b.y]);
        pq.add(new Cell(0, 0));

        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            int curTime = dist[cur.x][cur.y];

            for (int[] d : DIRS) {
                int nextX = cur.x + d[0];
                int nextY = cur.y + d[1];

                if (
                        isValidCell(n, m, nextX, nextY) &&
                        Math.max(curTime, grid[nextX][nextY]) + 1 < dist[nextX][nextY]
                ) {
                    dist[nextX][nextY] = Math.max(curTime, grid[nextX][nextY]) + 1;
                    pq.add(new Cell(nextX, nextY));
                }
            }
        }

        return dist[n - 1][m - 1];
    }

    private boolean isValidCell(int rows, int cols, int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
