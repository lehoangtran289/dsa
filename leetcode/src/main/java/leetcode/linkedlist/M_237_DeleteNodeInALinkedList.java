package leetcode.linkedlist;

/**
 * Deleted node is guaranteed not to be the tail node in the linked list.
 */
public class M_237_DeleteNodeInALinkedList {

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public void deleteNode0(ListNode node) {
        ListNode cur = node, prev = null;

        while (cur.next != null) {
            cur.val = cur.next.val;
            prev = cur;
            cur = cur.next;
        }
        prev.next = null;
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    public void deleteNode1(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


}
