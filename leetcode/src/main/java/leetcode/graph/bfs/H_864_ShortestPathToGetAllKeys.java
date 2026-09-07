package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class H_864_ShortestPathToGetAllKeys {

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U, D, L, R

    /**
     * Idea: BFS + Bitmask
     * ---
     * Maintain a queue of states [x, y, steps, key mask]
     *      and a 3D array minSteps[x][y][mask] to store the minimum steps to reach (x, y) with a given key mask state.
     * ---
     * TC: O(rows * cols * 2^keyCount)
     * SC: O(rows * cols * 2^keyCount)
     */
    public int shortestPathAllKeys(String[] grid) {
        int rows = grid.length, cols = grid[0].length();
        int startX = 0, startY = 0;
        int keyCount = 0;
        int[] keyToId = new int[26]; // map key cell to integer id

        // find starting points and counting number of keys
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                char cell = grid[i].charAt(j);

                if (cell == '@') {
                    startX = i;
                    startY = j;
                }

                if (Character.isLowerCase(cell)) {
                    keyToId[cell - 'a'] = keyCount;
                    keyCount++;
                }
            }
        }
        int fullMask = (1 << keyCount) - 1;

        // init BFS states
        boolean[][][] visited = new boolean[rows][cols][1 << keyCount];
        Queue<int[]> queue = new ArrayDeque<>(); // [x, y, steps, mask]

        visited[startX][startY][0] = true;
        queue.add(new int[]{startX, startY, 0, 0});

        // BFS
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0], curY = cur[1];
            int curSteps = cur[2];
            int curMask = cur[3];

            // ending condition: find all keys
            if (curMask == fullMask) return curSteps;

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (!isCellValid(nextX, nextY, rows, cols)) continue;

                char nextCell = grid[nextX].charAt(nextY);
                if (nextCell == '#') continue;

                int nextMask = curMask;
                if (Character.isUpperCase(nextCell)) {
                    // check if mask contains corresponding key
                    int keyId = keyToId[Character.toLowerCase(nextCell) - 'a'];
                    if (!containsKey(nextMask, keyId)) continue; // missing key
                } else if (Character.isLowerCase(nextCell)) {
                    nextMask = addKey(nextMask, keyToId[nextCell - 'a']);
                }

                int nextSteps = curSteps + 1;
                if (visited[nextX][nextY][nextMask]) continue;

                visited[nextX][nextY][nextMask] = true;
                queue.add(new int[]{nextX, nextY, nextSteps, nextMask});
            }
        }

        return -1;
    }

    private boolean containsKey(int mask, int pos) {
        return ((mask >> pos) & 1) == 1;
    }

    private int addKey(int mask, int keyId) {
        return mask | (1 << keyId);
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
