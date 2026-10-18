package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class M_739_DailyTemperatures {
    static void main() {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        // [1, 1, 4, 2, 1, 1, 0, 0]
    }

    /**
     * Idea: monotonic stack, store prev index of element
     * ---
     * Dry run:
     * [73,74,75,71,69,72,76,73]
     * i = 0 -> stack = [0], res = [0,0,0,0,0,0,0,0]
     * i = 1 -> stack = [1], res = [1,0,0,0,0,0,0,0]
     * i = 2 -> stack = [2], res = [1,1,0,0,0,0,0,0]
     * i = 3 -> stack = [2,3]
     * i = 4 -> stack = [2,3,4]
     * i = 5 -> stack = [2,5], res = [1,1,0,2,1,0,0,0]
     * i = 6 -> stack = [6], res = [1,1,4,2,1,1,0,0]
     * i = 7 -> stack = [6, 7] => res = [1,1,4,2,1,1,0,0]
     * ---
     * TC: O(n)
     * SC: O(2 * n)
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int prevIdx = stack.pop();
                res[prevIdx] = i - prevIdx;
            }
            stack.push(i);
        }

        return res;
    }
}
