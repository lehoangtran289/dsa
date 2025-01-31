package leetcode.graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class H_827_MakingALargeIsland {
    public static void main(String[] args) {
        System.out.println(largestIsland(new int[][]{{1, 1}, {1, 1}}));
        System.out.println(largestIsland(new int[][]{{0, 0}, {0, 0}}));
        System.out.println(largestIsland(new int[][]{{1, 0, 1}, {0, 1, 0}}));
        System.out.println(largestIsland(new int[][]{{1, 1}, {1, 0}}));
    }

    public static int largestIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // left, right, up, down
        Map<Integer, Integer> islandSizeMap = new HashMap<>(); // id, size

        int res = 0;
        int idx = 2;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1) {
                    int curSize = calSize(grid, dirs, i, j, idx);
                    islandSizeMap.put(idx, curSize);
                    idx++;
                    res = Math.max(res, curSize);
                }
            }
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] != 0) continue;

                Set<Integer> connected = new HashSet<>();
                for (int[] d : dirs) {
                    int nextX = i + d[0];
                    int nextY = j + d[1];
                    if (isCellValid(nextX, nextY, rows, cols)) {
                        connected.add(grid[nextX][nextY]);
                    }
                }

                int sum = 0;
                for (int id : connected) {
                    sum += islandSizeMap.getOrDefault(id, 0);
                }
                res = Math.max(res, sum + 1);
            }
        }

        return res;
    }

    private static int calSize(int[][] grid, int[][] dirs, int x, int y, int idx) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Cell> queue = new ArrayDeque<>();
        queue.add(new Cell(x, y));
        grid[x][y] = idx;
        visited[x][y] = true;

        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                Cell cur = queue.poll();
                res++;

                for (int[] d : dirs) {
                    int nextX = cur.x + d[0];
                    int nextY = cur.y + d[1];

                    if (
                            isCellValid(nextX, nextY, grid.length, grid[0].length) &&
                            !visited[nextX][nextY] &&
                            grid[nextX][nextY] == 1
                    ) {
                        grid[nextX][nextY] = idx;
                        visited[nextX][nextY] = true;
                        queue.add(new Cell(nextX, nextY));
                    }
                }
            }
        }

        return res;
    }

    private static boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
