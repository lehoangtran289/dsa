package leetcode.array.array2d;

import java.util.HashSet;
import java.util.Set;

public class M_874_WalkingRobotSimulation {

    /**
     * Simulation
     * -----
     * TC: O(m + n) where m is the length of commands and n is the length of obstacles
     * SC: O(n) for the hash set of obstacles
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        final int[][] DIRS = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // U, L, D, R in Oxy
        Set<Long> obstacleSet = new HashSet<>();
        int curDir = 0;
        int[] curState = new int[]{0, 0};
        int res = 0;

        for (int[] obstacle : obstacles) {
            obstacleSet.add(hash(obstacle[0], obstacle[1]));
        }

        for (int command : commands) {
            if (command == -1) {
                curDir = (curDir + 3) % 4;
            } else if (command == -2) {
                curDir = (curDir + 1) % 4;
            } else {
                for (int i = 0; i < command; ++i) {
                    int nextX = curState[0] + DIRS[curDir][0];
                    int nextY = curState[1] + DIRS[curDir][1];

                    if (obstacleSet.contains(hash(nextX, nextY))) break;
                    curState[0] = nextX;
                    curState[1] = nextY;
                }
                res = Math.max(res, getDistance(curState));
            }
        }

        return res;
    }

    private int getDistance(int[] pos) {
        return pos[0] * pos[0] + pos[1] * pos[1];
    }

    private long hash(int x, int y) {
        return (long) x * (int) 1e5 + y;
    }
}
