package leetcode.graphtheory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class H_2290_MinimumObstacleRemovalToReachCorner {
    public static void main(String[] args) {
        System.out.println(minimumObstacles(new int[][]{{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}}));
        System.out.println(minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}));
    }

    static class Cell {
        int x;
        int y;
        int weight;

        public Cell(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        public String toString() {
            return x + "|" + y + "|" + weight;
        }
    }

    public static int minimumObstacles(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dist = new int[rows][cols];
        for (int i = 0; i < rows; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Cell(0, 0, 0));

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();

            for (int[] dir : dirs) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols) {
                    int d = dist[cur.x][cur.y] + grid[nextX][nextY];
                    if (d < dist[nextX][nextY]) {
                        dist[nextX][nextY] = d;
                        pq.add(new Cell(nextX, nextY, d));
                    }
                }
            }
        }

        return dist[rows - 1][cols - 1];
    }
}
