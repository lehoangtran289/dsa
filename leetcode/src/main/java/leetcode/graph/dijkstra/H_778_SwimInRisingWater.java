package leetcode.graph.dijkstra;

import java.util.PriorityQueue;

public class H_778_SwimInRisingWater {
    private final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // U, D, R, L

    /**
     * Dijkstra's algorithm, maintain max weight to reach each cell
     * -----------------------
     * TC: O(N^2 * logN)
     *      Expand N^2 cells, each cell operation in log(N) time
     * SC: O(N^2)
     */
    public int swimInWater(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;

        // frame: [x, y, time]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        boolean[][] visited = new boolean[rows][cols];

        minHeap.add(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;

        // Dijkstra
        while (!minHeap.isEmpty()) {
            int[] state = minHeap.poll();
            int curX = state[0], curY = state[1], curTime = state[2];

            if (curX == rows - 1 && curY == cols - 1)
                return curTime;

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0], nextY = curY + dir[1];

                if (
                        isValidCell(nextX, nextY, rows, cols)
                        && !visited[nextX][nextY]
                ) {
                    int nextTime = Math.max(curTime, grid[nextX][nextY]);
                    minHeap.add(new int[]{nextX, nextY, nextTime});
                    visited[nextX][nextY] = true;
                }
            }
        }

        return -1;
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
