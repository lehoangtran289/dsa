package leetcode.linkedlist;

public class M_2130_MaximumTwinSumOfALinkedList {

    /**
     * Fast Slow Pointers + Reverse Linked List
     * ---
     * TC: O(n)
     * SC: O(1)
     */
    public int pairSum(ListNode head) {
        // find the half point
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half
        // a -> b -> c => a <- b <- c
        ListNode prev = null, cur = slow, nextNode = null;
        while (cur != null) {
            nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }

        // traverse both halves to get twin sum
        int res = 0;

        ListNode start = head, end = prev;
        while (end != null) {
            res = Math.max(res, start.val + end.val);
            start = start.next;
            end = end.next;
        }

        return res;
    }
}
