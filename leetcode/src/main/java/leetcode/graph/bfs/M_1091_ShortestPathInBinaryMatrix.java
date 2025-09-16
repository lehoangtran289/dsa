package leetcode.graph.bfs;

import java.util.*;

public class M_1091_ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        System.out.println(shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

    static class Cell {
        int x;
        int y;
        int dist;

        Cell(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    /**
     * BFS
     * -----------------------
     * TC: O(N^2)
     * SC: O(N^2)
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        final int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        Queue<Cell> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        queue.add(new Cell(0, 0, 1));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Cell cur = queue.poll();

            if (cur.x == n - 1 && cur.y == n - 1) return cur.dist;

            for (int[] d : dirs) {
                int nextX = cur.x + d[0];
                int nextY = cur.y + d[1];
                int nextDist = cur.dist + 1;

                if (
                        isCellValid(nextX, nextY, n, n)
                        && !visited[nextX][nextY]
                        && grid[nextX][nextY] == 0
                ) {
                    queue.add(new Cell(nextX, nextY, nextDist));
                    visited[nextX][nextY] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
