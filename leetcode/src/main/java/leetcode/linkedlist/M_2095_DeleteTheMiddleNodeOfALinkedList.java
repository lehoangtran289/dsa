package leetcode.linkedlist;

public class M_2095_DeleteTheMiddleNodeOfALinkedList {

    /**
     * 1 pass - slow and fast pointer
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head, fast = head, prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // delete the middle node
        prev.next = slow.next;

        return head;
    }

    /**
     * 2 passes
     */
    public ListNode deleteMiddle1(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode ptr = head;
        int count = 0;

        while (ptr != null) {
            count++;
            ptr = ptr.next;
        }

        int middle = count / 2 - 1;
        ptr = head;

        while (middle-- > 0) {
            ptr = ptr.next;
        }

        ptr.next = ptr.next.next;
        return head;
    }
}
