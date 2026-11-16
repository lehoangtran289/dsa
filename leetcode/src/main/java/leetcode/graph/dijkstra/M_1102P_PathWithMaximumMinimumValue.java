package leetcode.graph.dijkstra;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class M_1102P_PathWithMaximumMinimumValue {

    private final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // L, R, U, D

    /**
     * DIJKSTRA
     * Idea: maximin path -> Dijkstra where "score" = min cell value along the path
     * ---
     * TC: O(rows * cols * log(rows * cols))
     * SC: O(rows * cols)
     */
    public int maximumMinimumPath(int[][] grid) {
        final int rows = grid.length, cols = grid[0].length;

        // best[x][y] = max "min value" over all paths (0,0) -> (x,y)
        int[][] best = new int[rows][cols];
        for (int[] row : best) Arrays.fill(row, -1);

        // max-heap states: [curX, curY, curScore]
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));
        best[0][0] = grid[0][0];
        maxHeap.add(new int[]{0, 0, grid[0][0]});

        // process Dijkstra
        while (!maxHeap.isEmpty()) {
            int[] curCell = maxHeap.poll();
            int curX = curCell[0], curY = curCell[1], curScore = curCell[2];

            // pruning
            if (curScore < best[curX][curY]) continue;
            if (curX == rows - 1 && curY == cols - 1) return curScore;

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (!isCellValid(nextX, nextY, rows, cols)) continue;

                // minimum value along this route
                int nextScore = Math.min(curScore, grid[nextX][nextY]);

                if (nextScore > best[nextX][nextY]) {
                    best[nextX][nextY] = nextScore;
                    maxHeap.add(new int[]{nextX, nextY, nextScore});
                }
            }
        }

        return -1;
    }

    // ------------------------------------------------------------------------------

    /**
     * BINARY SEARCH + BFS
     * Idea2: maximum minimum -> binary search on results
     * ~ Check if there exists a path having cell values >= target -> BFS
     * ---
     * TC: O(logN * rows * cols), N = max values in grid
     * SC: O(rows * cols)
     */
    public int maximumMinimumPath2(int[][] grid) {
        int res = 0;

        int l = 0, r = 1 << 30;
        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (isValid(grid, mid)) {
                res = Math.max(res, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    /**
     * Check if there is a path having cell values >= target
     * -> Path score at least = target
     */
    private boolean isValid(int[][] grid, int target) {
        // base case
        if (grid[0][0] < target) return false;

        final int rows = grid.length, cols = grid[0].length;

        // BFS states: [curX, curY]
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.add(new int[]{0, 0});

        // process BFS
        while (!queue.isEmpty()) {
            int[] curCell = queue.poll();
            int curX = curCell[0], curY = curCell[1];

            if (curX == rows - 1 && curY == cols - 1) return true;

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (
                        isCellValid(nextX, nextY, rows, cols)
                        && !visited[nextX][nextY]
                        && grid[nextX][nextY] >= target
                ) {
                    queue.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }

        return false;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
