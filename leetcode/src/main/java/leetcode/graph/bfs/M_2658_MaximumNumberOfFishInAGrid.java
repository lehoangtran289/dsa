package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_2658_MaximumNumberOfFishInAGrid {
    public static void main(String[] args) {
        System.out.println(findMaxFish(new int[][]{{0, 2, 1, 0}, {4, 0, 0, 3}, {1, 0, 0, 4}, {0, 3, 2, 0}}));
    }

    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int findMaxFish(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] != 0) {
                    int fishCount = bfs(grid, i, j, visited);
                    res = Math.max(res, fishCount);
                }
            }
        }
        return res;
    }

    private static int bfs(int[][] grid, int i, int j, boolean[][] visited) {
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // left, right, up, down

        Queue<Cell> queue = new ArrayDeque<>();
        queue.add(new Cell(i, j));
        visited[i][j] = true;

        int fishCount = grid[i][j];
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();

            for (int[] dir : dirs) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (
                        isCellValid(grid, nextX, nextY, grid.length, grid[0].length) &&
                        !visited[nextX][nextY]
                ) {
                    fishCount += grid[nextX][nextY];
                    visited[nextX][nextY] = true;
                    queue.add(new Cell(nextX, nextY));
                }
            }
        }
        return fishCount;
    }

    private static boolean isCellValid(int[][] grid, int r, int c, int rows, int cols) {
        return r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] != 0;
    }
}
