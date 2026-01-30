package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_286_WallsAndGates {

    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int INF = 2147483647;
    private static final int GATE = 0;

    /**
     * BFS - Multi-source from all gates simultaneously.
     * ------------------------
     * Time: O(m * n)
     * Space: O(m * n)
     */
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[0].length; ++j) {
                if (rooms[i][j] == GATE) queue.add(new int[]{i, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] curCell = queue.poll();
            int x = curCell[0];
            int y = curCell[1];

            for (int[] dir : DIRS) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];

                if (isValidCell(rooms, nextX, nextY) && rooms[nextX][nextY] == INF) {
                    rooms[nextX][nextY] = rooms[x][y] + 1;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
    }

    private boolean isValidCell(int[][] rooms, int x, int y) {
        return x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length;
    }
}
