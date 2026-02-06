package leetcode.array.stack;

import java.util.ArrayDeque;

public class M_2211_CountCollisionsOnARoad {
    public static void main(String[] args) {
        System.out.println(countCollisions("RLRSLL")); // 5
    }

    /**
     * Stack
     * -----------------------------
     * - Use a stack to simulate the cars on the road.
     * - Process each car based on its direction:
     * - 'R': Push onto the stack.
     * - 'S': Resolve all 'R' cars on the stack (they collide and become 'S').
     * - 'L': Check the top of the stack:
     * - If it's 'S', res++
     * - If it's 'L', push onto the stack.
     * - If it's 'R', resolve the collision (both become 'S') and continue resolving any subsequent 'R' cars.
     * -----------------------------
     * - Time Complexity: O(n) where n is the length of directions string.
     * - Space Complexity: O(n) in the worst case for the stack.
     */
    public static int countCollisions(String directions) {
        int res = 0;
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : directions.toCharArray()) {
            if (c == 'L') {
                if (stack.isEmpty()) {
                    stack.addFirst('L');
                    continue;
                }

                if (stack.peekFirst() == 'S') {
                    res++;
                } else if (stack.peekFirst() == 'L') {
                    stack.addFirst('L');
                } else if (stack.peekFirst() == 'R') {
                    res += 2;
                    stack.pollFirst();

                    // now top = S
                    while (!stack.isEmpty() && stack.peekFirst() == 'R') {
                        res++;
                        stack.pollFirst();
                    }
                    stack.addFirst('S');
                }
            }

            if (c == 'S') {
                while (!stack.isEmpty() && stack.peekFirst() == 'R') {
                    res++;
                    stack.pollFirst();
                }
                stack.addFirst('S');
            }

            if (c == 'R') {
                stack.addFirst('R');
            }
        }

        return res;
    }
}
