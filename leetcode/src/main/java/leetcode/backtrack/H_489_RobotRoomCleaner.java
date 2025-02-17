package leetcode.backtrack;

import java.util.HashSet;
import java.util.Set;

public class H_489_RobotRoomCleaner {
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    private static final int[][] DIRS = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void cleanRoom(Robot robot) {
        Set<Cell> visited = new HashSet<>();
        backtrack(robot, visited, 0, 0, 0);
    }

    private static void backtrack(Robot robot, Set<Cell> visited, int x, int y, int curDir) {
        visited.add(new Cell(x, y));
        robot.clean();

        for (int i = 0; i < 4; ++i) {
            int newDir = (curDir + i) % 4;
            int nextX = x + DIRS[newDir][0];
            int nextY = y + DIRS[newDir][1];

            if (
                    !visited.contains(new Cell(nextX, nextY)) &&
                    robot.move()
            ) {
                backtrack(robot, visited, nextX, nextY, newDir);
                goBack(robot);
            }
            robot.turnRight(); // clockwise
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

