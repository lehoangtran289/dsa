package leetcode.linkedlist;

public class M_24_SwapNodesInParis {

    /**
     * Idea: Use a dummy node to store the head
     * Iterate through the list in pairs, swapping the nodes and updating the pointers accordingly.
     * ---
     * TC: O(n) - traverse the list once
     * SC: O(1) - only a few pointers are used
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy, cur = head;

        // check if there are at least two nodes to swap
        while (cur != null && cur.next != null) {
            // store the two nodes to be swapped
            ListNode node1 = cur;
            ListNode node2 = cur.next;

            // swap
            prev.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            // update iterator pointers
            prev = node1;
            cur = node1.next;
        }

        return dummy.next;
    }
}
