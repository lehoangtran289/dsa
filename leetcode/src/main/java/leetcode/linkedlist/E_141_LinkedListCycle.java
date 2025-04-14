package leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class E_141_LinkedListCycle {

    /**
     *  ------------------------------------------------
     *  Intuitive solution
     *  - Use a HashSet to store the nodes we have seen
     *  ------------------------------------------------
     *  TC O(n)
     *  SC O(n)
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        seen.add(head);

        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            if (seen.contains(cur)) return true;
            seen.add(cur);
        }

        return false;
    }

    /**
     *  ------------------------------------------------
     *  Fast and Slow pointer
     *  - Use two pointers, one moving twice as fast as the other
     * <br>
     *  -> Useful for detecting cycles in linked lists
     *  ------------------------------------------------
     *  TC O(n)
     *  SC O(1)
     */
    public boolean hasCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) return true;
        }

        return false;
    }
}
