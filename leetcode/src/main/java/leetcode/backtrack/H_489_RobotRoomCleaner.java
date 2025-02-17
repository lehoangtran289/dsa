package leetcode.backtrack;

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
public class H_489_RobotRoomCleaner {
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    private static final int[][] DIRS = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void cleanRoom(Robot robot) {
        Set<Cell> visited = new HashSet<>();
        backtrack(robot, visited, 0, 0, 0);
    }

    // we keep curDir to know the direction we are facing
    // the order is important since the idea is always to turn right
    private static void backtrack(Robot robot, Set<Cell> visited, int x, int y, int curDir) {
        visited.add(new Cell(x, y));
        robot.clean();

        for (int i = 0; i < 4; ++i) {
            int newDir = (curDir + i) % 4;
            int nextX = x + DIRS[newDir][0];
            int nextY = y + DIRS[newDir][1];

            // Move forward if there is no obstacle in front of the robot.
            if (
                    !visited.contains(new Cell(nextX, nextY)) &&
                    robot.move()
            ) {
                backtrack(robot, visited, nextX, nextY, newDir); // Explore next cells backtrack(new_cell, new_direction)
                goBack(robot); // Backtrack, i.e. go back to the previous cell.
            }
            robot.turnRight(); // Turn right because now there is an obstacle (or a virtual obstacle) just in front.
        }
    }

    private static void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }

        @Override
        public boolean equals(Object obj) {
            Cell cell = (Cell) obj;
            return this.x == cell.x && this.y == cell.y;
        }
    }

    interface Robot {
        void turnLeft();
        void turnRight();
        boolean move();
        void clean();
    }

}

