package leetcode.linkedlist;

import java.util.Stack;

public class E_92_ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(head);
        ListNode reversedHead = reverseBetween2(head, 2, 4);
        System.out.println(reversedHead);
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------
     * Intuitive using stack
     * TC: O(n)
     * SC: O(n)
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        Stack<Integer> stack = new Stack<>();

        ListNode cur = head;
        int id = 1;
        while (cur != null) {
            if (left <= id && id <= right) {
                stack.add(cur.val);
            }
            id++;
            cur = cur.next;
        }

        cur = head;
        id = 1;
        while (cur != null) {
            if (left <= id && id <= right) {
                cur.val = stack.pop();
            }
            id++;
            cur = cur.next;
        }

        return head;
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * 1 pass approach
     * TC: O(n)
     * SC: O(1)
     * ----------------------------------------------------------------------------------------------------------------
     */
    public static ListNode reverseBetween2(ListNode head, int left, int right) {
        // If there is only one node or no need to reverse, return the original list.
        if (head.next == null || left == right) {
            return head;
        }

        // Dummy node to simplify the handling of the head node.
        ListNode dummyNode = new ListNode(0, head);

        // Pointer to track the node before the reversal section.
        ListNode nodeBeforeReverse = dummyNode;
        for (int i = 0; i < left - 1; ++i) {
            nodeBeforeReverse = nodeBeforeReverse.next;
        }

        // 'firstNode' will become the last node after the reversal.
        ListNode firstNode = nodeBeforeReverse.next;
        // 'current' is used to track the current node being processed.
        ListNode cur = firstNode;
        ListNode prev = null;

        // Perform the actual reversal between 'left' and 'right'.
        for (int i = left; i <= right; ++i) {
            ListNode nextTemp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextTemp;
        }

        // Reconnect the reversed section back to the list.
        nodeBeforeReverse.next = prev;      // Connect with node before reversed part.
        firstNode.next = cur;       // Connect the last reversed node to the remainder of the list.

        // Return the new head of the list.
        return dummyNode.next;
    }
}
