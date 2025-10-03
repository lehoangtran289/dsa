package leetcode.graph.dijkstra;

import java.util.PriorityQueue;
import java.util.Queue;

public class H_407_TrappingRainWaterII {
    public static void main(String[] args) {
        System.out.println(trapRainWater(new int[][]{
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        })); // 4
    }

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

    public static int trapRainWater(int[][] heightMap) {
        final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // L, R, U, D
        int rows = heightMap.length, cols = heightMap[0].length;

        int res = 0;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Cell> pq = new PriorityQueue<>((a, b) -> a.h - b.h);

        // init 4 edges
        for (int i = 0; i < rows; ++i) {
            visited[i][0] = true;
            visited[i][cols - 1] = true;
            pq.add(new Cell(i, 0, heightMap[i][0]));
            pq.add(new Cell(i, cols - 1, heightMap[i][cols - 1]));
        }

        for (int j = 0; j < cols; ++j) {
            visited[0][j] = true;
            visited[rows - 1][j] = true;
            pq.add(new Cell(0, j, heightMap[0][j]));
            pq.add(new Cell(rows - 1, j, heightMap[rows - 1][j]));
        }

        // BFS
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();

            for (int[] dir : DIRS) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (!isCellValid(nextX, nextY, rows, cols) || visited[nextX][nextY]) continue;

                int nextH = heightMap[nextX][nextY];

                // If the neighbor's height < cur height, water can be trapped
                if (cur.h > nextH) {
                    res += cur.h - nextH;
                    nextH = cur.h;
                }
                pq.add(new Cell(nextX, nextY, nextH));
                visited[nextX][nextY] = true;
            }
        }

        return res;
    }

    private static boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
