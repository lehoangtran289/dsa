package leetcode.graph;

import java.util.PriorityQueue;

public class H_407_TrappingRainWaterII {
    public static void main(String[] args) {

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
        int rows = heightMap.length;
        int cols = heightMap[0].length;

        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.h - b.h);

        // Add the first and last row cells to the boundary and mark them as visited
        for (int i = 0; i < cols; ++i) {
            pq.add(new Cell(0, i, heightMap[0][i]));
            pq.add(new Cell(rows - 1, i, heightMap[rows - 1][i]));
            visited[0][i] = true;
            visited[rows - 1][i] = true;
        }

        // Add the first and last column cells to the boundary and mark them as visited
        for (int i = 0; i < rows; ++i) {
            pq.add(new Cell(i, 0, heightMap[i][0]));
            pq.add(new Cell(i, cols - 1, heightMap[i][cols - 1]));
            visited[i][0] = true;
            visited[i][cols - 1] = true;
        }

        int res = 0;

        // Direction arrays
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // left, right, up, down

        // Process cells in the boundary (min-heap will always pop the smallest height)
        while (!pq.isEmpty()) {
            // Pop the cell with the smallest height from the boundary
            Cell cur = pq.poll();

            // Explore all 4 neighboring cells
            for (int[] dir : dirs) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (
                        isValidCell(nextX, nextY, rows, cols) &&
                        !visited[nextX][nextY]
                ) {
                    int nextH = heightMap[nextX][nextY];

                    // If the neighbor's height is less than the current boundary height, water can be trapped
                    if (nextH < cur.h) {
                        res += cur.h - nextH;

                        // Push the neighbor into the boundary with updated height (to prevent water leakage)
                        pq.add(new Cell(nextX, nextY, cur.h));
                    } else {
                        pq.add(new Cell(nextX, nextY, nextH));
                    }

                    visited[nextX][nextY] = true;
                }
            }

        }

        return res;
    }

    private static boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }
}
