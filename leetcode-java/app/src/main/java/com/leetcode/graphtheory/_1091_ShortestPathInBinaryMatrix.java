package com.leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class _1091_ShortestPathInBinaryMatrix {
public static void main(String[] args) {
        System.out.println(shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

    public static int shortestPathBinaryMatrix2(int[][] grid) {
        int n = grid.length;
        grid[0][0] = 1;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});

        for (int ans = 1; !q.isEmpty(); ++ans) {
            for (int k = q.size(); k > 0; --k) {
                var p = q.poll();
                int i = p[0], j = p[1];
                if (i == n - 1 && j == n - 1) {
                    return ans;
                }
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                            grid[x][y] = 1;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        int len = grid.length;
        if (grid[0][0] == 1 || grid[len - 1][len - 1] == 1)
            return -1;

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        boolean[][] visited = new boolean[len][len];
        Deque<int[]> queue = new ArrayDeque<>();

        visited[0][0] = true;
        queue.add(new int[]{0, 0, 1}); // r, c, distance

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == len - 1 && cur[1] == len - 1) // reach dest
                return cur[2];

            for (int[] direction : directions) {
                int r = cur[0] + direction[0];
                int c = cur[1] + direction[1];

                if (Math.min(r, c) < 0 || Math.max(r, c) >= len
                        || grid[r][c] == 1 || visited[r][c])
                    continue;

                visited[r][c] = true;
                queue.add(new int[]{r, c, cur[2] + 1}); // r, c, new distance
            }
        }
        return -1;
    }
}
