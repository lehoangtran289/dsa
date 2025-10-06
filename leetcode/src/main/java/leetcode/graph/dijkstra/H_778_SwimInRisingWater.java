package leetcode.graph.dijkstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class H_778_SwimInRisingWater {
    private final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // U, D, R, L

    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Dijkstra's algorithm, maintain max weight to reach each cell
     * -----------------------
     * TC: O(N^2 * logN)
     *      Expand N^2 cells, each cell operation in log(N) time
     * SC: O(N^2)
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        // define Dijkstra variables
        int[][] weights = new int[n][n];
        Queue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> grid[a.x][a.y]));

        // init Dijkstra data
        for (int[] w : weights) {
            Arrays.fill(w, Integer.MAX_VALUE);
        }
        weights[0][0] = grid[0][0];
        pq.add(new Cell(0, 0));

        // process Dijkstra
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            int curW = weights[cur.x][cur.y];

            for (int[] dir : DIRS) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (
                        isCellValid(nextX, nextY, n, n)
                        && Math.max(curW, grid[nextX][nextY]) < weights[nextX][nextY]
                ) {
                    weights[nextX][nextY] = Math.max(curW, grid[nextX][nextY]);
                    pq.add(new Cell(nextX, nextY));
                }
            }
        }

        return weights[n - 1][n - 1];
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
