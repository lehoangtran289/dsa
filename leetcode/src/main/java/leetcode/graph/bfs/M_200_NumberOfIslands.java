package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

public class M_200_NumberOfIslands {
    static int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '0', '0'},
                {'1', '0', '0', '1', '1', '0', '1', '1'},
                {'0', '0', '0', '0', '0', '0', '1', '0'},
                {'1', '1', '1', '0', '0', '0', '1', '0'},
                {'1', '1', '1', '0', '0', '0', '0', '0'},
                {'1', '1', '1', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '1', '1', '0', '0', '1'}};
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    public static void bfs(char[][] grid, int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] direction : directions) {
                int newR = cur[0] + direction[0];
                int newC = cur[1] + direction[1];

                if (newR < 0 || newR >= grid.length || newC < 0 || newC >= grid[0].length
                    || grid[newR][newC] != '1') // out of bounds image or not island
                    continue;

                queue.offer(new int[]{newR, newC});
                grid[newR][newC] = '2';
            }
        }
    }
}
