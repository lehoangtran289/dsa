package leetcode.graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_1765_MapOfHighestPeak {
    static class Cell {
        int x;
        int y;
        int h;

        Cell(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    public static int[][] highestPeak(int[][] isWater) {
        int rows = isWater.length;
        int cols = isWater[0].length;
        int[][] res = new int[rows][cols];

        boolean[][] isVisited = new boolean[rows][cols];
        Queue<Cell> pq = new ArrayDeque<>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (isWater[i][j] == 1) {
                    pq.add(new Cell(i, j, 0));
                    isVisited[i][j] = true;
                }
            }
        }

        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // left, right, up, down
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();

            for (int[] dir : dirs) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (
                        isValidCell(nextX, nextY, rows, cols)
                        && !isVisited[nextX][nextY]
                ) {
                    res[nextX][nextY] = cur.h + 1;
                    isVisited[nextX][nextY] = true;
                    pq.add(new Cell(nextX, nextY, res[nextX][nextY]));
                }
            }
        }

        return res;
    }

    private static boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }
}
