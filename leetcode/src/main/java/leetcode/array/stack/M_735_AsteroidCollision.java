package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class M_735_AsteroidCollision {
    static void main() {
        System.out.println(Arrays.toString(asteroidCollision(new int[]{-2, -2, 1, -2}))); // [-2, -2, -2]
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        // Consider cases: > >, > <, < <, < >
        // If > 0 or stack[-1] < 0 -> push
        for (int asteroid : asteroids) {
            if (stack.isEmpty() || asteroid >= 0 || stack.peekFirst() < 0) {
                stack.addFirst(asteroid);
                continue;
            }

            int curSize = Math.abs(asteroid);
            boolean addNewAsteroid = false;

            while (!stack.isEmpty() && stack.peekFirst() > 0) {
                int prevSize = Math.abs(stack.peekFirst());

                if (prevSize < curSize) { // stack top is destroyed
                    stack.pollFirst();
                    addNewAsteroid = true;
                } else if (prevSize == curSize) { // both is destroyed
                    addNewAsteroid = false;
                    stack.pollFirst();
                    break;
                } else { // current asteroid is destroyed
                    addNewAsteroid = false;
                    break;
                }
            }

            if (addNewAsteroid) stack.addFirst(asteroid);
        }

        // construct output
        int[] res = new int[stack.size()];
        int index = res.length - 1;

        while (!stack.isEmpty()) {
            res[index--] = stack.pollFirst();
        }
        return res;
    }
}
