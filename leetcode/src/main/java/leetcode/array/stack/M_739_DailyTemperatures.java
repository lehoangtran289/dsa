package leetcode.array.stack;

import java.util.Stack;

public class M_739_DailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temperatures);
        for (int days : result) {
            System.out.print(days + " ");
        }
        // Output: [1, 1, 4, 2, 1, 1, 0, 0]
    }

    /**
     * Monotonic Stack
     * TC: O(n)
     * SC: O(2 * n)
     */
    public static int[] dailyTemperatures(int[] temp) {
        int n = temp.length;
        Stack<int[]> stack = new Stack<>(); // [val, index]
        int[] answer = new int[n];

        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && temp[i] > stack.peek()[0]) {
                int prevIdx = stack.pop()[1];
                answer[prevIdx] = i - prevIdx;
            }
            stack.push(new int[] {temp[i], i});
        }

        return answer;
    }

    /**
     * Reduced space complexity
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] dailyTemperatures2(int[] temp) {
        int n = temp.length;
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temp[i] > temp[stack.peek()]) {
                int prevIdx = stack.pop();
                answer[prevIdx] = i - prevIdx;
            }
            stack.push(i);
        }

        return answer;
    }
}
