package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class H_317_ShortestDistanceFromAllBuildings {
    private final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // left, right, up, down
    private int totalHouses = 0;

    public int shortestDistance(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    totalHouses++;
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 0) {
                    int dist = bfs(grid, i, j);
                    if (dist != -1) res = Math.min(res, dist);
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int bfs(int[][] grid, int i, int j) {
        int n = grid.length, m = grid[0].length;

        // bfs vars
        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<Cell> queue = new ArrayDeque<>();

        // calculating vars
        int totalDist = 0;
        int vistedHouses = 0;

        // init vars
        visited[i][j] = true;
        queue.add(new Cell(i, j));

        while (!queue.isEmpty()) {
            Cell cur = queue.poll();

            for (int[] dir : DIRS) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];
                if (
                        isValid(grid.length, grid[0].length, nextX, nextY)
                        && !visited[nextX][nextY]
                ) {
                    dist[nextX][nextY] = dist[cur.x][cur.y] + 1;
                    visited[nextX][nextY] = true;

                    if (grid[nextX][nextY] == 0) { // only process if cell = 0
                        queue.add(new Cell(nextX, nextY));
                    } else if (grid[nextX][nextY] == 1) { // if cell = 1 -> take distance
                        totalDist += dist[nextX][nextY];
                        vistedHouses++;
                    }
                }
            }
        }

        // if cannot visit all 1s -> return -1
        return vistedHouses == totalHouses ? totalDist : -1;
    }

    private boolean isValid(int n, int m, int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
