package leetcode.array.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class M_215_KthLargestElementInAnArray {
    public static void main(String[] args) {
        System.out.println(new M_215_KthLargestElementInAnArray().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(new M_215_KthLargestElementInAnArray().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(new M_215_KthLargestElementInAnArray().findKthLargest(new int[]{1}, 1));
    }

    /**
     * Min Heap size k
     * TC: O(n * log(k)) since operation cost in heap is O(log(k))
     * SC: O(k)
     */
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>(); // min heap

        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        return queue.peek();
    }
}
