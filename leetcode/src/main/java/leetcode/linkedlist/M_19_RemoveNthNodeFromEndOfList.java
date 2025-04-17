package leetcode.linkedlist;

public class M_19_RemoveNthNodeFromEndOfList {
    /**
     * ---------------------------------------------
     * 2 pass approach
     * ----------------------------------------------
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        int delIndex = length - n;

        // edge case when there is 1 node (n <= sz)
        if (length == 1) return null;

        // get prev node of delete node
        cur = head;
        for (int i = 0; i < delIndex - 1; ++i) {
            cur = cur.next;
        }

        // if not end node -> handle first node and normal node
        if (delIndex != length - 1) {
            if (delIndex == 0) return cur.next; // delete head node
            cur.next = cur.next.next;
        } else { // handle end node
            cur.next = null;
        }

        return head;
    }

    /**
     * ---------------------------------------------
     * 1 pass approach
     * ----------------------------------------------
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode left = dummy;
        ListNode right = dummy;

        // Move right pointer n + 1 steps ahead -> gap between left and right is n
        for (int i = 0; i <= n; ++i) {
            right = right.next;
        }

        // Move both pointers until right reaches the end
        // At this point, left is at the node before the one we want to remove
        while (right != null) {
            left = left.next;
            right = right.next;
        }

        // Remove the nth node from the end
        left.next = left.next.next;

        return dummy.next;
    }
}
