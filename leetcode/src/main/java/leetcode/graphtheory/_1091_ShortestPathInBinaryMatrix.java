package leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1091_ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        System.out.println(shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
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
