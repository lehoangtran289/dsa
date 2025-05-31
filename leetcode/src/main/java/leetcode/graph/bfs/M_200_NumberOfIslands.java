package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_200_NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '0', '0'},
                {'1', '0', '0', '1', '1', '0', '1', '1'},
                {'0', '0', '0', '0', '0', '0', '1', '0'},
                {'1', '1', '1', '0', '0', '0', '1', '0'},
                {'1', '1', '1', '0', '0', '0', '0', '0'},
                {'1', '1', '1', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '1', '1', '0', '0', '1'}};
        System.out.println(numIslands(grid));
    }

    private static final int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up

    public static int numIslands(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int res = 0;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res++;
                    bfs(grid, visited, i, j);
                }
            }
        }

        return res;
    }

    /**
     * bfs from (row, col) to all connected '1's
     */
    private static void bfs(
            char[][] grid,
            boolean[][] visited,
            int row,
            int col
    ) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] d : DIRS) {
                int nextX = cur[0] + d[0];
                int nextY = cur[1] + d[1];

                if (
                        isValidCell(nextX, nextY, grid.length, grid[0].length) &&
                        grid[nextX][nextY] == '1' &&
                        !visited[nextX][nextY]
                ) {
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
    }

    private static boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
