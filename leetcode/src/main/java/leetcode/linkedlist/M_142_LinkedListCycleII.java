package leetcode.linkedlist;

/**
 * Return the first node that causes the cycle
 */
public class M_142_LinkedListCycleII {

    /**
     * Fast and slow pointer approach
     * Let's define a as path length from start to the entrance of the cycle.
     * Let's define b as path length from entrance to the meeting point of 2 pointers
     * Let's define c as the total length of the cycle.
     * ---
     * When the fast pointer meets the slow pointer, we have:
     * - fast pointer has traveled a + b + k * c (k is the number of cycles)
     * - slow pointer has traveled a + b ~ fast pointer has traveled 2 * (a + b)
     * => a + b + k * c = 2 * (a + b)
     * => a + b = k * c
     * ----
     *
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;

        // Move fast pointer to the start of the list
        fast = head;

        // Move both pointers at the same speed
        // The point where they meet is the entrance to the cycle
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
}
