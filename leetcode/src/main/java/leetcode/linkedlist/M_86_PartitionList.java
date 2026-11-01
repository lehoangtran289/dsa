package leetcode.linkedlist;

public class M_86_PartitionList {

    /**
     * Idea: create 2 lists to store smaller and bigger elements correspondingly
     * Note: remember to remove bigger ptr at the end to avoid cycle when connecting 2 lists
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(-1);
        ListNode bigHead = new ListNode(-1);

        ListNode smallPtr = smallHead;
        ListNode bigPtr = bigHead;

        while (head != null) {
            if (head.val < x) {
                smallPtr.next = head;
                smallPtr = smallPtr.next;
            } else {
                bigPtr.next = head;
                bigPtr = bigPtr.next;
            }
            head = head.next;
        }

        bigPtr.next = null; // remove cycle
        smallPtr.next = bigHead.next; // connect 2 lists
        return smallHead.next;
    }
}
