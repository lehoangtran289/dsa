package leetcode.linkedlist;

public class M_1721_SwappingNodesInALinkedList {

    /**
     * Given the head of a linked list, swap the values of the k-th node from the beginning and the k-th node from the end.
     * -----------------------
     * Idea: swap values using 2 passes
     * -----------------------
     * TC: O(n)
     * SC: O(1)
     */
    public ListNode swapNodes(ListNode head, int k) {
        // count number of nodes
        int count = 0;
        ListNode cur = head;

        while (cur != null) {
            count++;
            cur = cur.next;
        }

        // find k-th node from start and end
        cur = head;

        int startIdx = k - 1;
        int endIdx = count - k;
        ListNode startNode = null;
        ListNode endNode = null;

        for (int i = 0; i < count; ++i) {
            if (i == startIdx) {
                startNode = cur;
            }
            if (i == endIdx) {
                endNode = cur;
            }
            cur = cur.next;
        }

        // process swap values
        int temp = startNode.val;
        startNode.val = endNode.val;
        endNode.val = temp;

        return head;
    }
}
