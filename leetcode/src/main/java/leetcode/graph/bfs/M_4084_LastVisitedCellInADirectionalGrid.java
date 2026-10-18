package leetcode.graph.bfs;

import java.util.Map;

public class M_4084_LastVisitedCellInADirectionalGrid {

    private static final Map<Character, int[]> DIRS = Map.of(
            'L', new int[]{0, -1},
            'R', new int[]{0, 1},
            'U', new int[]{-1, 0},
            'D', new int[]{1, 0}
    );

    /**
     * TC: O(rows * cols)
     * SC: O(rows * cols)
     */
    public int[] lastVisitedCell(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int x = 0, y = 0;
        visited[x][y] = true;

        while (true) {
            int[] d = DIRS.get(grid[x][y]);
            int nextX = x + d[0], nextY = y + d[1];

            if (!isValidCell(nextX, nextY, rows, cols) || visited[nextX][nextY]) {
                return new int[]{x, y};
            }

            visited[nextX][nextY] = true;
            x = nextX;
            y = nextY;
        }
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
