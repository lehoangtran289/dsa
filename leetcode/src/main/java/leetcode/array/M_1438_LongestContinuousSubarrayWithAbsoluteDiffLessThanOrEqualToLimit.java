package leetcode.array;

import java.util.PriorityQueue;

public class M_1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
    }

    static class Num {
        int n;
        int idx;

        Num(int n, int idx) {
            this.n = n;
            this.idx = idx;
        }
    }

    public static int longestSubarray(int[] nums, int limit) {
        int res = 0;
        PriorityQueue<Num> minHeap = new PriorityQueue<>((a, b) -> a.n - b.n);
        PriorityQueue<Num> maxHeap = new PriorityQueue<>((a, b) -> b.n - a.n);

        int l = 0;
        for (int r = 0; r < nums.length; ++r) {
            minHeap.add(new Num(nums[r], r));
            maxHeap.add(new Num(nums[r], r));

            while (maxHeap.peek().n - minHeap.peek().n > limit) {
                l = Math.min(maxHeap.peek().idx, minHeap.peek().idx) + 1;

                // Remove elements from the heaps that are outside the current window
                while (maxHeap.peek().idx < l) {
                    maxHeap.poll();
                }
                while (minHeap.peek().idx < l) {
                    minHeap.poll();
                }
            }
            res = Math.max(res, Math.abs(r - l) + 1);
        }

        return res;
    }
}
