package leetcode.linkedlist;

public class E_1290_ConvertBinaryNumberInALinkedListToInteger {

    /**
     * 101
     * step1: res = 1
     * step2: res = 1 * 2 + 0
     * step3: res = (1 * 2 + 0) * 2 + 1 = 5
     * --------------
     * res = res * 2 + p.val
     *
     * --------------
     * TC: O(N)
     * SC: O(1)
     */
    public int getDecimalValue(ListNode head) {
        int res = 0;

        ListNode pointer = head;
        while (pointer != null) {
            res = res * 2 + pointer.val;
            pointer = pointer.next;
        }

        return res;
    }
}
