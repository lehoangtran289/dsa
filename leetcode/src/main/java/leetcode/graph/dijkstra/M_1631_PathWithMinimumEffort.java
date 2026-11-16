package leetcode.graph.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M_1631_PathWithMinimumEffort {

    private static final int INF = 1 << 30;
    private final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // L, R, U, D

    /**
     * Idea: minimax path -> Dijkstra where "distance" = max edge diff along the path
     * ~ relax: nextEffort = max(curEffort, |grid[cur] - grid[next]|), keep if smaller
     * ~ min-heap => the first time we pop the target, its effort is already optimal
     * ---
     * TC: O(rows * cols * log(rows * cols))
     * SC: O(rows * cols)
     */
    public int minimumEffortPath(int[][] grid) {
        final int rows = grid.length, cols = grid[0].length;

        // best[x][y] = minimum effort required to reach cell (x, y)
        int[][] best = new int[rows][cols];
        for (int[] row : best) Arrays.fill(row, INF);

        // min-heap states: [curEffort, curX, curY]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        best[0][0] = 0;
        pq.add(new int[]{0, 0, 0});

        // process Dijkstra
        while (!pq.isEmpty()) {
            int[] curCell = pq.poll();
            int curEffort = curCell[0], curX = curCell[1], curY = curCell[2];

            // skip stale entries
            if (curEffort > best[curX][curY]) continue;

            // target reached -> optimal answer
            if (curX == rows - 1 && curY == cols - 1) return curEffort;

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (!isCellValid(nextX, nextY, rows, cols)) continue;

                // maximum absolute diff along this route
                int nextEffort = Math.max(curEffort, Math.abs(grid[curX][curY] - grid[nextX][nextY]));

                // relaxation
                if (nextEffort < best[nextX][nextY]) {
                    best[nextX][nextY] = nextEffort;
                    pq.add(new int[]{nextEffort, nextX, nextY});
                }
            }
        }

        return -1;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
