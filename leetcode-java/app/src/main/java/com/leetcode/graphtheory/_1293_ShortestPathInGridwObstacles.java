package com.leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class _1293_ShortestPathInGridwObstacles {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}
        };
        System.out.println(shortestPath(grid, 1));
    }

    /**
     * Q "how do you know when to increment the step?"
     * -> count how many times "while(size-- > 0) { }" have been called.
     * First Call: the 'while loop'  will process cell (0,0) and terminate
     * Second Call: the 'while loop' will process all cells ONE step away from (0,0)
     * Third Call: the loop will process all cells TWO steps away from (0,0).......AND SO ON
     * that means number of while loop calls == number of steps taken.
     */
    public static int shortestPath(int[][] grid, int k) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][m][k+1]; // k+1 since k is not index
        int res = 0;

        visited[0][0][k] = true;
        q.offer(new int[]{0, 0, k}); // row, col, K balance
        while (!q.isEmpty()) {
            int size = q.size();

            while (size > 0) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int remainK = cur[2];

                if (r == n - 1 && c == m - 1) // reach dest
                    return res;

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                    if (grid[nr][nc] == 1) { // meet obstacles -> decrease remainK
                        if (remainK > 0 && !visited[nr][nc][remainK - 1]) {
                            visited[nr][nc][remainK - 1] = true;
                            q.offer(new int[]{nr, nc, remainK - 1});
                        }
                    } else { // no obstacle
                        if (!visited[nr][nc][remainK]) {
                            visited[nr][nc][remainK] = true;
                            q.offer(new int[]{nr, nc, remainK});
                        }
                    }
                }
                --size;
            }
            ++res;
        }
        return -1;
    }
}
