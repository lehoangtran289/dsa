package leetcode.treeheap;

import java.util.PriorityQueue;

public class _215_KthLargestElementInAnArray {
    public static void main(String[] args) {
        System.out.println(new _215_KthLargestElementInAnArray().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(new _215_KthLargestElementInAnArray().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(new _215_KthLargestElementInAnArray().findKthLargest(new int[]{1}, 1));
    }
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                pq.offer(nums[i]);
                continue;
            }
            if (pq.peek() < nums[i]) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        return pq.peek();
    }
}
