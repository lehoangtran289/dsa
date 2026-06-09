package leetcode.backtrack;

import leetcode.utils.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. What to do if, after the right turn, there is an obstacle just in front? </br>
 * >> Turn right again. </br>
 * 2. How to explore the alternative paths from the cell?</br>
 * >> Go back to that cell and then turn right from your last explored direction.</br>
 * 3. When to stop? </br>
 * >> Stop when you explored all possible paths, i.e. all 4 directions (up, right, down, and left) for each visited cell.</br>
 */
public class H_489P_RobotRoomCleaner {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void cleanRoom(Robot robot) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        backtrack(robot, visited, 0, 0, 0);
    }

    // The idea is always to turn right
    private static void backtrack(
            Robot robot,
            Set<Pair<Integer, Integer>> visited,
            int x,
            int y,
            int dir
    ) {
        visited.add(new Pair<>(x, y));
        robot.clean();

        for (int i = 0; i < 4; ++i) {
            int nextDir = (dir + i) % 4;
            int nextX = x + DIRS[nextDir][0];
            int nextY = y + DIRS[nextDir][1];

            // Move forward if possible
            if (
                    !visited.contains(new Pair<>(nextX, nextY)) &&
                    robot.move()
            ) {
                backtrack(robot, visited, nextX, nextY, nextDir); // Explore next cells
                goBack(robot); // Backtrack, i.e. go back to the previous cell.
            }
            robot.turnRight(); // Turn right to explore next direction
        }
    }

    private static void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    interface Robot {
        void turnLeft();

        void turnRight();

        boolean move();

        void clean();
    }

}

