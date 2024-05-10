package leetcode.divideconquer;

import java.util.PriorityQueue;

public class _148_SortList {
    /**
     * Given an array with a normal distribution, Quicksort and Heapsort will both run in O(n log(n)).
     * <br/>But Quicksort will execute faster because its constant factors are smaller than the constant factors for Heapsort.
     * <br/> To put it simply, partitioning is faster than maintaining the heap.
     */

    public static void main(String[] args) {
        ListNode r = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        sortListWithMergeSort(r);
    }

    public static ListNode sortListWithMergeSort(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = head, right = head;

        while (right != null && right.next != null) {
            mid = mid.next;
            right = right.next.next;
        }

        ListNode l1 = sortListWithMergeSort(head);
        ListNode l2 = sortListWithMergeSort(mid);

        return merge(l1, l2);
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0), temp = res;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 != null)
            temp.next = l1;
        else if (l2 != null)
            temp.next = l2;

        return res.next;
    }

    public ListNode sortListWithHeap(ListNode head) {
        if (head == null || head.next == null) return head;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (head != null) {
            pq.add(head.val);
            head = head.next;
        }
        ListNode root = new ListNode(pq.poll(), null);
        ListNode temp = root;
        while (!pq.isEmpty()) {
            Integer val = pq.poll();
            temp.next = new ListNode(val, null);
            temp = temp.next;
        }
        return root;
    }


    static class ListNode {
        int val;
        ListNode next;

        @Override
        public String toString() {
            return val + " ";
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
