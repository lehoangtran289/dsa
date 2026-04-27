package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_1559_DetectCyclesIn2DGrid {
    public boolean containsCycle(char[][] grid) {
        final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // L, R, U, D
        int rows = grid.length, cols = grid[0].length;

        // DFS vars
        boolean[][] visited = new boolean[rows][cols];
        Queue<Cell> queue = new ArrayDeque<>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (visited[i][j]) continue;

                // init
                char pathChar = grid[i][j];
                queue.add(new Cell(i, j, -1, -1));
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    Cell cur = queue.poll();
                    int prevX = cur.prevX;
                    int prevY = cur.prevY;

                    for (int[] dir : DIRS) {
                        int nextX = cur.x + dir[0];
                        int nextY = cur.y + dir[1];

                        if (
                                !isValidCell(nextX, nextY, rows, cols)
                                || grid[nextX][nextY] != pathChar
                        ) continue;

                        if (!visited[nextX][nextY]) {
                            visited[nextX][nextY] = true;
                            queue.add(new Cell(nextX, nextY, cur.x, cur.y));
                            continue;
                        }

                        if (nextX != prevX && nextY != prevY) return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private static class Cell {
        int x;
        int y;
        int prevX;
        int prevY;

        Cell(int x, int y, int prevX, int prevY) {
            this.x = x;
            this.y = y;
            this.prevX = prevX;
            this.prevY = prevY;
        }
    }
}
