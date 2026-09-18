package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class H_84_LargestRectangleInHistogram {

    /**
     * Idea: Fix a height, try to expand to its left and right to the maximum -> max rectangle at a certain height point
     * So we need a way to find the nearest smaller element on the left and on the right of an index.
     *
     * -> We can use monotonic stack for this.
     * When we encounter an index with smaller height, pop from stack until we meet a smaller height index.
     * The stack's top is now the nearest smaller height index.
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int[] left = findSmallerLeft(heights);
        int[] right = findSmallerRight(heights);

        for (int i = 0; i < heights.length; ++i) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }

        return res;
    }

    private int[] findSmallerLeft(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // If stack is empty, no smaller element on the left exists, default value = -1
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return res;
    }

    private int[] findSmallerRight(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // If stack is empty, no smaller element on the right exists, default value = n
            res[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        return res;
    }
}
