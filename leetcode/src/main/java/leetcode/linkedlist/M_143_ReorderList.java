package leetcode.linkedlist;

public class M_143_ReorderList {

    public void reorderList(ListNode head) {
        if (head == null) return;

        // find mid point
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // reverse end half
        ListNode cur = slow, prev = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        // zipping two lists that share common ending node
        ListNode p1 = head, p2 = prev;
        while (p2.next != null) {
            ListNode temp1 = p1.next;
            ListNode temp2 = p2.next;

            p1.next = p2;
            p1 = temp1;
            p2.next = p1;
            p2 = temp2;
        }
    }
}
