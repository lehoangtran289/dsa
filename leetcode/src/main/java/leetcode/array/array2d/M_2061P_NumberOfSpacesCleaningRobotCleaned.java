package leetcode.array.array2d;

public class M_2061P_NumberOfSpacesCleaningRobotCleaned {
    private final int[][] DIRS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // R, D, L, U

    /**
     * Simulation
     * ---
     * TC: O(mn) each cell can be visited at most 4 times (4 directions)
     * SC: O(mn) for visited states
     */
    public int numberOfCleanRooms(int[][] room) {
        int rows = room.length, cols = room[0].length;
        boolean[][][] stateVisited = new boolean[rows][cols][4];
        int direction = 0;
        Cell cur = new Cell(0, 0);
        int res = 0;

        while (true) {
            if (stateVisited[cur.x][cur.y][direction]) break;
            stateVisited[cur.x][cur.y][direction] = true;

            if (room[cur.x][cur.y] == 0) {
                room[cur.x][cur.y] = -1;
                res++;
            }

            Cell next = getNextCell(cur, DIRS[direction]);
            if (!isCellValid(next.x, next.y, rows, cols)
                || room[next.x][next.y] == 1
            ) {
                direction = (direction + 1) % 4;
                continue;
            }

            cur = next;
        }

        return res;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private Cell getNextCell(Cell cur, int[] dir) {
        return new Cell(cur.x + dir[0], cur.y + dir[1]);
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
