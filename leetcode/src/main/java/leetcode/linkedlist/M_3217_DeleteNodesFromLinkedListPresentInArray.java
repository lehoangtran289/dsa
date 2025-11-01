package leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class M_3217_DeleteNodesFromLinkedListPresentInArray {

    /**
     * Set
     * ---------------------------
     * TC: O(n + m)
     * SC: O(m)
     */
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);

        while (head != null && numSet.contains(head.val)) {
            head = head.next;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        while (cur.next != null) {
            if (numSet.contains(cur.next.val)) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
