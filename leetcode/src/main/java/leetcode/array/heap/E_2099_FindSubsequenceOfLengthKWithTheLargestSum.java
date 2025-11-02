package leetcode.array.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class E_2099_FindSubsequenceOfLengthKWithTheLargestSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSubsequence(new int[]{3, 4, 3, 3, 5}, 2))); // [4, 5]
    }

    /**
     * Find top k-th
     * --------------------
     * TC: O(n * log(k))
     * SC: O(k)
     */
    public static int[] maxSubsequence(int[] nums, int k) {
        // find top k-th largest element
        // <num, index> min heap
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < nums.length; ++i) {
            minHeap.add(new int[]{nums[i], i});
            if (minHeap.size() > k) minHeap.poll();
        }

        // with k-th largest element, sort its index
        Queue<int[]> minHeap2 = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        while (!minHeap.isEmpty()) {
            minHeap2.add(minHeap.poll());
        }

        // build result based on sorted index
        int[] res = new int[k];
        int i = 0;
        while (!minHeap2.isEmpty()) {
            res[i++] = minHeap2.poll()[0];
        }

        return res;
    }
}
