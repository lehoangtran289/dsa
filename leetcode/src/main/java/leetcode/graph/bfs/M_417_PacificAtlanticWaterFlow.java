package leetcode.graph.bfs;

import java.util.*;

public class M_417_PacificAtlanticWaterFlow {
    private final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // N, S, E, W
    private int[][] heights;
    private int rows;
    private int cols;

    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * BFS from both oceans, then find intersection
     * -----------------------
     * TC: O(M * N)
     * SC: O(M * N)
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.rows = heights.length;
        this.cols = heights[0].length;

        boolean[][] pacificVisited = new boolean[rows][cols];
        boolean[][] atlanticVisited = new boolean[rows][cols];

        // bfs pacific & atlantic separately
        Queue<Cell> pacificQueue = new ArrayDeque<>();
        Queue<Cell> atlanticQueue = new ArrayDeque<>();

        for (int i = 0; i < rows; ++i) {
            pacificQueue.add(new Cell(i, 0));
            atlanticQueue.add(new Cell(i, cols - 1));
            pacificVisited[i][0] = true;
            atlanticVisited[i][cols - 1] = true;
        }
        for (int j = 0; j < cols; ++j) {
            pacificQueue.add(new Cell(0, j));
            atlanticQueue.add(new Cell(rows - 1, j));
            pacificVisited[0][j] = true;
            atlanticVisited[rows - 1][j] = true;
        }

        bfs(pacificQueue, pacificVisited);
        bfs(atlanticQueue, atlanticVisited);

        // find intersection
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    res.add(List.of(i, j));
                }
            }
        }

        return res;
    }

    private void bfs(
            Queue<Cell> queue,
            boolean[][] visited
    ) {
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();

            for (int[] d : DIRS) {
                int nextX = cur.x + d[0];
                int nextY = cur.y + d[1];

                if (
                        isValidCell(nextX, nextY, rows, cols)
                        && !visited[nextX][nextY]
                        && heights[nextX][nextY] >= heights[cur.x][cur.y]
                ) {
                    queue.add(new Cell(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
