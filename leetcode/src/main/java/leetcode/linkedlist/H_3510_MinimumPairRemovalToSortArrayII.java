package leetcode.linkedlist;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class H_3510_MinimumPairRemovalToSortArrayII {

    /**
     * Linked List + Min Heap + lazy deletion
     * ---
     * Idea: Use a linked list to represent the current state of the array, and a min-heap to always merge the pair with the smallest sum.
     * Lazily delete merged nodes to avoid complex heap updates.
     * ----------------------------------
     * TC: O(n log n)
     * SC: O(n)
     */
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.sum == b.sum) return Integer.compare(a.first.index, b.first.index);
            return Long.compare(a.sum, b.sum);
        });

        // build linked list and min heap
        int decreaseCount = 0;
        Node prev = new Node(0, nums[0]);

        for (int i = 1; i < n; ++i) {
            Node cur = new Node(i, nums[i]);
            prev.next = cur;
            cur.prev = prev;

            minHeap.add(new HeapNode(prev.val + cur.val, prev, cur));
            if (cur.val < prev.val) {
                decreaseCount++;
            }
            prev = cur;
        }

        // process min heap until array is sorted non-decreasing
        Set<Node> merged = new HashSet<>(); // to track merged nodes for lazy deletion
        int res = 0;

        while (decreaseCount > 0 && !minHeap.isEmpty()) {
            HeapNode cur = minHeap.poll();
            Node first = cur.first;
            Node second = cur.second;
            long curSum = cur.sum;

            if (!merged.contains(first) && !merged.contains(second)) {
                if (first.val > second.val) decreaseCount--;

                Node prevNode = first.prev;
                Node nextNode = second.next;

                Node newNode = new Node(first.index, curSum);
                newNode.prev = prevNode;
                newNode.next = nextNode;

                if (prevNode != null) {
                    minHeap.add(new HeapNode(prevNode.val + curSum, prevNode, newNode));
                    prevNode.next = newNode;

                    if (prevNode.val > first.val && prevNode.val <= curSum) decreaseCount--;
                    else if (prevNode.val <= first.val && prevNode.val > curSum) decreaseCount++;
                }

                if (nextNode != null) {
                    minHeap.add(new HeapNode(curSum + nextNode.val, newNode, nextNode));
                    nextNode.prev = newNode;

                    if (nextNode.val < second.val && nextNode.val >= curSum) decreaseCount--;
                    else if (nextNode.val >= second.val && nextNode.val < curSum) decreaseCount++;
                }

                merged.add(first);
                merged.add(second);
                res++;
            }
        }

        return res;
    }

    static class Node {
        int index;
        long val;
        Node prev;
        Node next;

        public Node(int index, long val) {
            this.index = index;
            this.val = val;
        }
    }

    static class HeapNode {
        long sum;
        Node first;
        Node second;

        public HeapNode(long sum, Node first, Node second) {
            this.sum = sum;
            this.first = first;
            this.second = second;
        }
    }
}
