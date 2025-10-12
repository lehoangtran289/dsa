package leetcode.array.stack;

import java.util.Arrays;
import java.util.Stack;

public class M_739_DailyTemperatures {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73}))
        ); // [1, 1, 4, 2, 1, 1, 0, 0]
    }

    /**
     * Monotonic Stack
     * TC: O(n)
     * SC: O(2 * n)
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Stack<int[]> stack = new Stack<>(); // [val, index]
        int[] answer = new int[n];

        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                int prevIdx = stack.pop()[1];
                answer[prevIdx] = i - prevIdx;
            }
            stack.push(new int[] {temperatures[i], i});
        }

        return answer;
    }

    /**
     * Reduced space complexity
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> tempIdStack = new Stack<>();
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            while (
                    !tempIdStack.isEmpty()
                    && temperatures[tempIdStack.peek()] < temperatures[i]
            ) {
                int prevIdx = tempIdStack.pop();
                res[prevIdx] = i - prevIdx;
            }
            tempIdStack.add(i);
        }

        return res;
    }
}
