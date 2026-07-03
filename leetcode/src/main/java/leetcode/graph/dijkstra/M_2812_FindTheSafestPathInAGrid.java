package leetcode.graph.dijkstra;

import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class M_2812_FindTheSafestPathInAGrid {

    private static final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // L, R, U, D

    /**
     * Multi-src BFS + Dijkstra
     * 1. Multi-src BFS to find the distance of each cell to the nearest thief
     * 2. Dijkstra to find the path with max safeness factor (maxheap), answer = min safeness factor in that path
     * ---
     * TC: O(n^2 log n)
     * SC: O(n^2)
     */
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        // Multi-src BFS
        int[][] dist = buildDist(grid); // O(n^2)

        // Dijkstra -> Find path with max safeness (maxheap), answer = min safeness in that path
        return minDistInMaxPath(dist); // O(n^2 logn)
    }

    /**
     * Multi-src BFS + Binary Search + BFS
     * 1. Multi-src BFS to find the distance of each cell to the nearest thief
     * 2. Binary search on the safeness factor, and check if a path exists
     * ---
     * TC: O(n^2 log n)
     * SC: O(n^2)
     */
    public int maximumSafenessFactor2(List<List<Integer>> grid) {
        // Multi-src BFS
        int[][] dist = buildDist(grid); // O(n^2)

        // Binary search res -> Path must have safeness factor >= target
        int res = 0;
        int l = 0, r = 1000;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(dist, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    private int[][] buildDist(List<List<Integer>> grid) {
        int n = grid.size();
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid.get(i).get(j) == 1) {
                    queue.add(new int[]{i, j});
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = 1 << 30;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : DIRS) {
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];

                if (
                        isCellValid(nextX, nextY, n, n)
                        && dist[nextX][nextY] == 1 << 30
                ) {
                    dist[nextX][nextY] = dist[cur[0]][cur[1]] + 1;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        return dist;
    }

    private int minDistInMaxPath(int[][] dist) {
        int n = dist.length;

        int[][] maxDist = new int[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);

        // init dijkstra
        pq.add(new int[]{0, 0, dist[0][0]});
        maxDist[0][0] = dist[0][0];

        while (!pq.isEmpty()) {
            int[] curCell = pq.poll();
            int curDist = curCell[2];

            if (curCell[0] == n - 1 && curCell[1] == n - 1) {
                return curDist;
            }

            for (int[] dir : DIRS) {
                int nextX = curCell[0] + dir[0];
                int nextY = curCell[1] + dir[1];

                if (!isCellValid(nextX, nextY, n, n)) continue;

                // Take min safeness in a path
                int nextDist = Math.min(curDist, dist[nextX][nextY]);

                if (nextDist > maxDist[nextX][nextY]) {
                    maxDist[nextX][nextY] = nextDist;
                    pq.add(new int[]{nextX, nextY, nextDist});
                }
            }
        }
        return 0;
    }

    private boolean isValid(int[][] dist, int target) {
        int n = dist.length;
        if (dist[0][0] < target || dist[n - 1][n - 1] < target) return false;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curCell = queue.poll();

            if (curCell[0] == n - 1 && curCell[1] == n - 1) return true;

            for (int[] dir : DIRS) {
                int nextX = curCell[0] + dir[0];
                int nextY = curCell[1] + dir[1];

                if (
                        isCellValid(nextX, nextY, n, n)
                        && !visited[nextX][nextY]
                        && dist[nextX][nextY] >= target
                ) {
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        return false;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
