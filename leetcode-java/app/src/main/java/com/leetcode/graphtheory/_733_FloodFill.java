package com.leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.Deque;

public class _733_FloodFill {
    public static void main(String[] args) {
        int[][] image = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1}};
        int[][] result = floodFill(image, 4, 4, 5);
        for (int[] row : result) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    // flood fill using bfs
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int prevColor = image[sr][sc];
        int lr = image.length;
        int lc = image[0].length;
        boolean[][] visited = new boolean[lr][lc];

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            image[cur[0]][cur[1]] = color;

            // check all 4 directions of cur cell
            for (int[] direction : directions) {
                int r = cur[0] + direction[0];
                int c = cur[1] + direction[1];

                if (r < 0 || r >= lr || c < 0 || c >= lc) // out of bounds image
                    continue;

                if (visited[r][c] || image[r][c] != prevColor) // already visited or not same color
                    continue;

                queue.add(new int[]{r, c});
                visited[r][c] = true;
            }
        }
        return image;
    }
}
