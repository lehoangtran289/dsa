package leetcode.graph.bfs;

import java.util.Stack;

public class H_1970_LastDayWhereYouCanStillCross {

    private static final int[][] DIRS = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * Binary Search + DFS
     * ------------------------
     * Time: O(row⋅col⋅log(row⋅col))
     * Space: O(row⋅col)
     */
    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = cells.length;

        int res = 1;
        int l = 1, r = n;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (isValid(row, col, cells, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    private boolean isValid(int row, int col, int[][] cells, int day) {
        int[][] grid = new int[row][col];

        for (int i = 0; i < day; ++i) {
            int[] waterCell = cells[i];
            int x = waterCell[0] - 1, y = waterCell[1] - 1;

            grid[x][y] = 1;
        }

        Stack<Cell> stack = new Stack<>();
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < col; ++i) {
            if (grid[0][i] == 0) {
                stack.push(new Cell(0, i));
                visited[0][i] = true;
            }
        }

        while (!stack.isEmpty()) {
            Cell cell = stack.pop();

            if (cell.x == row - 1) return true;

            for (int[] dir : DIRS) {
                int nextX = cell.x + dir[0];
                int nextY = cell.y + dir[1];

                if (
                        isValidCell(nextX, nextY, row, col) &&
                        !visited[nextX][nextY] &&
                        grid[nextX][nextY] == 0
                ) {
                    stack.push(new Cell(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }

        return false;
    }

    private boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x < row && y >= 0 && y < col;
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
