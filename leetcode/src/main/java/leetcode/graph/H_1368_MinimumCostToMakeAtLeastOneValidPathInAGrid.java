package leetcode.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class H_1368_MinimumCostToMakeAtLeastOneValidPathInAGrid {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,1,1,1},
                {2,2,2,2},
                {1,1,1,1},
                {2,2,2,2}
        };
        System.out.println(minCost(grid));
    }

    static class Cell {
        int x;
        int y;
        int cost;

        Cell(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static int minCost(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Track minimum cost to reach each cell
        int[][] costs = new int[rows][cols];
        for (int[] cost : costs) {
            Arrays.fill(cost, Integer.MAX_VALUE);
        }
        costs[0][0] = 0;

        // Dijkstra, min heap ordered by cost
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Cell(0, 0, costs[0][0]));

        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // left, right, up, down
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();

            // if better path already been found -> skip
            if (cur.cost > costs[cur.x][cur.y]) continue;

            // try 4 directions
            for (int i = 0; i < dirs.length; ++i) {
                int nextX = cur.x + dirs[i][0];
                int nextY = cur.y + dirs[i][1];

                if (isValidCell(nextX, nextY, rows, cols)) {
                    // Add cost = 1 if we need to change direction
                    boolean isDirMatch = grid[cur.x][cur.y] - 1 == i;
                    int newCost = cur.cost + (isDirMatch ? 0 : 1);

                    if (costs[nextX][nextY] > newCost) {
                        costs[nextX][nextY] = newCost;
                        pq.add(new Cell(nextX, nextY, newCost));
                    }
                }
            }

        }

        return costs[rows - 1][cols - 1];
    }

    private static boolean isValidCell(int x, int y, int n, int m) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
