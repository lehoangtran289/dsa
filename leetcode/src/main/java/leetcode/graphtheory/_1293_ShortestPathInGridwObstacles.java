package leetcode.graphtheory;

import java.util.ArrayDeque;
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
            ++res; // count "while(size-- > 0) { }" call times ~ distance from (0, 0) to (n-1, m-1)
        }
        return -1;
    }
}
