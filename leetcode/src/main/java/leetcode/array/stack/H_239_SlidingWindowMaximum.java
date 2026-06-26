package leetcode.array.stack;

import java.util.ArrayDeque;
import java.util.Arrays;

public class H_239_SlidingWindowMaximum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))
        ); // [3, 3, 5, 5, 6, 7]
    }

    /**
     * Idea: monotonic deque (decreasing), first element in queue = max
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int index = 0;
        ArrayDeque<Integer> maxDeque = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[i]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(i);

            if (maxDeque.peekFirst() + k <= i) {
                maxDeque.pollFirst();
            }

            if (i >= k - 1) res[index++] = nums[maxDeque.peekFirst()];
        }

        return res;
    }
}
