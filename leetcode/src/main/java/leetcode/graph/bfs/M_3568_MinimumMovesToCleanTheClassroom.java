package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class M_3568_MinimumMovesToCleanTheClassroom {

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U, D, L, R

    /**
     * Idea: BFS + Bitmask
     * ---
     * Maintain a queue of states [x, y, litter mask, energy, steps]
     *      and a 3D array bestEnergyMap[x][y][mask] to store the maximum energy at (x, y) with a given litter mask state.
     * Use BFS to explore all possible moves while collecting litter and managing energy.
     * ---
     * TC: O(rows * cols * energy * 2^litterCount) in the worst case, where litterCount is the number of litter positions.
     * SC: O(rows * cols * energy * 2^litterCount)
     */
    public int minMoves(String[] classroom, int energy) {
        int rows = classroom.length, cols = classroom[0].length();
        int startRow = 0, startCol = 0;
        int litterCount = 0;
        int[][] litterMap = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                // find starting position
                if (classroom[i].charAt(j) == 'S') {
                    startRow = i;
                    startCol = j;
                }
                // find litter positions
                if (classroom[i].charAt(j) == 'L') {
                    litterMap[i][j] = litterCount;
                    litterCount++;
                }
            }
        }
        int litterMask = (1 << litterCount) - 1; // e.g: 11111

        // bfs states
        Queue<int[]> queue = new ArrayDeque<>();
        int[][][] bestEnergyMap = new int[rows][cols][litterMask + 1]; // max energy at (x, y) with mask state

        // init bfs states
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                Arrays.fill(bestEnergyMap[i][j], -1);
            }
        }
        queue.add(new int[]{startRow, startCol, litterMask, energy, 0}); // [x, y, litter mask, energy, steps]
        bestEnergyMap[startRow][startCol][litterMask] = energy;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0], curY = cur[1];
            int curMask = cur[2];
            int curEnergy = cur[3];
            int curSteps = cur[4];

            if (curMask == 0) return curSteps;
            if (curEnergy == 0) continue;

            for (int[] dir : DIRS) {
                int nextX = curX + dir[0];
                int nextY = curY + dir[1];

                if (isCellValid(nextX, nextY, rows, cols)) {
                    char cell = classroom[nextX].charAt(nextY);
                    int nextEnergy = curEnergy - 1;
                    int nextMask = curMask;
                    int nextSteps = curSteps + 1;

                    if (cell == 'X') continue;
                    else if (cell == 'L') {
                        nextMask = collectLitter(curMask, litterMap[nextX][nextY]);
                    } else if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    // prune condition
                    if (nextEnergy <= bestEnergyMap[nextX][nextY][nextMask]) continue;

                    queue.add(new int[]{nextX, nextY, nextMask, nextEnergy, nextSteps});
                    bestEnergyMap[nextX][nextY][nextMask] = nextEnergy;
                }
            }
        }

        return -1;
    }

    private int collectLitter(int mask, int pos) {
        mask &= ~(1 << pos);
        return mask;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
