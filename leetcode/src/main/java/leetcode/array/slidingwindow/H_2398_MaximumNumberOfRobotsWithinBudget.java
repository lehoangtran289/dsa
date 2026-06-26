package leetcode.array.slidingwindow;

import java.util.ArrayDeque;

public class H_2398_MaximumNumberOfRobotsWithinBudget {
    public static void main(String[] args) {
        System.out.println(maximumRobots(new int[]{1, 3, 5, 2, 8}, new int[]{2, 1, 3, 4, 5}, 20)); // 2
        System.out.println(maximumRobots(new int[]{11, 12, 19}, new int[]{10, 8, 7}, 19)); // 0
    }

    /**
     * Prefix sum + Monotonic deque
     * ---
     * TC: O(n), where n is the length of chargeTimes
     * SC: O(n)
     */
    public static int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        long[] runningCostSum = new long[n + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // build prefix sum of running costs
        for (int i = 1; i <= n; ++i) {
            runningCostSum[i] = runningCostSum[i - 1] + runningCosts[i - 1];
        }

        // sliding window + deque for total cost
        int res = 0;
        int l = 0;

        for (int r = 0; r < n; ++r) {
            while (!deque.isEmpty() && chargeTimes[deque.peekLast()] <= chargeTimes[r]) {
                deque.pollLast();
            }
            deque.addLast(r);

            long k = r - l + 1;
            long totalCost = chargeTimes[deque.peekFirst()] + k * (runningCostSum[r + 1] - runningCostSum[l]);

            // shrink when > budget
            if (totalCost > budget) {
                if (deque.peekFirst() == l)
                    deque.pollFirst();
                l++;
            }

            res = Math.max(res, r - l + 1);
        }
        return res;
    }

}
