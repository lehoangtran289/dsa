package leetcode.array.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class H_2751_RobotCollisions {

    /**
     * TC: O(n logn)
     * SC: O(n)
     */
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;

        // init robot array and sort by position -> O(n * logn)
        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; ++i) {
            robots[i] = new Robot(i, positions[i], healths[i], directions.charAt(i));
        }
        Arrays.sort(robots, (a, b) -> Integer.compare(a.position, b.position));

        // init stack storing survival robots -> O(n)
        Stack<Robot> stack = new Stack<>();
        for (Robot curRobot : robots) {
            if (stack.isEmpty()) {
                stack.push(curRobot);
                continue;
            }

            boolean addNewRobot = false;
            while (!stack.isEmpty()) {
                Robot lastRobot = stack.peek();

                if (lastRobot.dir == curRobot.dir || curRobot.dir == 'R') {
                    addNewRobot = true;
                    break;
                }

                if (lastRobot.health < curRobot.health) {
                    stack.pop();
                    curRobot.health--;
                    if (curRobot.health == 0) break;
                    addNewRobot = true;
                } else if (lastRobot.health == curRobot.health) {
                    stack.pop();
                    addNewRobot = false;
                    break;
                } else {
                    lastRobot.health--;
                    if (lastRobot.health == 0) stack.pop();
                    addNewRobot = false;
                    break;
                }
            }
            if (addNewRobot) stack.push(curRobot);
        }

        // build result robot list, sort by original id -> O(n logn)
        return stack.stream()
                .sorted((a, b) -> Integer.compare(a.originalId, b.originalId))
                .map(e -> e.health)
                .collect(Collectors.toList());
    }

    static class Robot {
        int originalId;
        int position;
        int health;
        char dir;

        Robot(int originalId, int position, int health, char dir) {
            this.originalId = originalId;
            this.position = position;
            this.health = health;
            this.dir = dir;
        }
    }
}
