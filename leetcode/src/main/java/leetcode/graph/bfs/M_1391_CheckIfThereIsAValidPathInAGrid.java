package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_1391_CheckIfThereIsAValidPathInAGrid {

    /**
     * BFS
     * Trick: each cell has 2 ports, we need to check if the port connecting is same when we go to next cell
     * So we go to next cell && check if port connecting is same by traverse backward
     * --------
     */
    public boolean hasValidPath(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][][] valueToDir = {
                {{}, {}}, // 0
                {{0, -1}, {0, 1}}, // 1
                {{-1, 0}, {1, 0}}, // 2
                {{0, -1}, {1, 0}}, // 3
                {{0, 1}, {1, 0}}, // 4
                {{0, -1}, {-1, 0}}, // 5
                {{0, 1}, {-1, 0}} // 6
        };

        boolean[][] visited = new boolean[rows][cols];
        Queue<Cell> queue = new ArrayDeque<>();

        visited[0][0] = true;
        queue.add(new Cell(0, 0));

        while (!queue.isEmpty()) {
            Cell cur = queue.poll();
            int[][] dirs = valueToDir[grid[cur.x][cur.y]];

            for (int[] dir : dirs) {
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];

                if (!isCellValid(nx, ny, rows, cols) || visited[nx][ny]) continue;

                // go to next cell && check if port connecting is same
                int[][] nextDirs = valueToDir[grid[nx][ny]];
                for (int[] nextDir : nextDirs) {
                    if (nx + nextDir[0] == cur.x && ny + nextDir[1] == cur.y) {
                        visited[nx][ny] = true;
                        queue.add(new Cell(nx, ny));
                    }
                }
            }
        }

        return visited[rows - 1][cols - 1];
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
