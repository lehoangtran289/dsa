package leetcode.linkedlist;

public class M_61_RotateList {

    /**
     * Rotate a linked list to the right by k places.
     * ----------
     * TC: O(n)
     * SC: O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list and count number of nodes
        ListNode cur = head;
        int n = 1;

        while (cur.next != null) {
            cur = cur.next;
            n++;
        }
        cur.next = head;

        // find new tail = (n - k - 1)
        // new head = (n - k)
        k %= n;

        ListNode tail = head;
        int i = 0;
        while (i < n - k - 1) {
            tail = tail.next;
            i++;
        }

        // new head = tail.next;
        ListNode newHead = tail.next;
        tail.next = null;

        return newHead;
    }
}
