package leetcode.linkedlist;

public class M_2807_InsertGreatestCommonDivisorsInLinkedList {

    /**
     * ---------------------------------------------------------------
     * Iterate through the linked list and insert the GCD of each pair of adjacent nodes
     * ---------------------------------------------------------------
     * TC: O(n)
     * SC: O(1)
     */
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;

        while (cur != null && cur.next != null) {
            prev = cur;
            cur = cur.next;
            ListNode temp = new ListNode(gcd(prev.val, cur.val));
            prev.next = temp;
            temp.next = cur;
        }

        return head;
    }

    public int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}
