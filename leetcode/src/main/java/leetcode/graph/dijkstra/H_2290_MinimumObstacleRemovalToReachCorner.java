package leetcode.graph.dijkstra;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

public class H_2290_MinimumObstacleRemovalToReachCorner {
    public static void main(String[] args) {
        System.out.println(minimumObstacles(new int[][]{{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}}));
        System.out.println(minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}));
    }

    private static final int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * Dijkstra
     * ---
     * TC: O(n * m log(n * m))
     * SC: O(n * m)
     */
    public static int minimumObstacles(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int[] row : dist) Arrays.fill(row, 1 << 30);
        dist[0][0] = 0;
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int[] dir : DIRS) {
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];

                if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols) {
                    int nextWeight = cur[2] + grid[nextX][nextY];

                    if (nextWeight < dist[nextX][nextY]) {
                        dist[nextX][nextY] = nextWeight;
                        pq.add(new int[]{nextX, nextY, nextWeight});
                    }
                }
            }
        }

        return dist[rows - 1][cols - 1];
    }

    /**
     * 01 BFS
     * ---
     * TC: O(n * m)
     * SC: O(n * m)
     */
    public static int minimumObstacles2(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        Deque<int[]> deque = new ArrayDeque<>(); // {x, y, dist}

        for (int[] row : dist) Arrays.fill(row, 1 << 30);
        dist[0][0] = grid[0][0];
        deque.addFirst(new int[]{0, 0, dist[0][0]});

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();

            for (int[] dir : DIRS) {
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];

                if (
                        isCellValid(nextX, nextY, rows, cols)
                        && cur[2] + grid[nextX][nextY] < dist[nextX][nextY]
                ) {
                    int nextDist = cur[2] + grid[nextX][nextY];
                    dist[nextX][nextY] = nextDist;

                    if (grid[nextX][nextY] == 1) {
                        deque.addLast(new int[]{nextX, nextY, nextDist});
                    } else {
                        deque.addFirst(new int[]{nextX, nextY, nextDist});
                    }
                }
            }
        }

        return dist[rows - 1][cols - 1];
    }

    private static boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
