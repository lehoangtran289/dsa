package leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class E_3318_FindXSumOfAllKLongSubarraysI {

    /**
     * Simulation
     * ---------------------------
     * TC: O(n * k log k)
     * SC: O(k)
     */
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];

        for (int i = 0; i < res.length; ++i) {
            int start = i, end = i + k - 1;
            res[i] = xSum(nums, k, x, start, end);
        }

        return res;
    }

    private int xSum(int[] nums, int k, int x, int start, int end) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = start; i <= end; ++i) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        Queue<Integer> minHeap = new PriorityQueue<>(
                (a, b) -> freq.get(a) == freq.get(b) ? a - b : freq.get(a) - freq.get(b)
        );

        for (var key : freq.keySet()) {
            minHeap.add(key);
            if (minHeap.size() > x) minHeap.poll();
        }

        int res = 0;
        while (!minHeap.isEmpty()) {
            int key = minHeap.poll();
            res += key * freq.get(key);
        }
        return res;
    }
}
