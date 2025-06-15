package leetcode.linkedlist;

public class M_725_SplitLinkedListInParts {

    /**
     * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
     * ----------------------
     * Idea: create new linked lists for each part
     * ----------------------
     * TC: O(n)
     * SC: O(n)
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];

        // count number of nodes
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        // count part size & remain
        int parts = count / k;
        int remain = count % k;

        // process k elements in res list
        cur = head;

        for (int i = 0; i < k; ++i) {
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;

            // distribute remain
            int curSize = parts;
            if (remain > 0) {
                curSize++;
                remain--;
            }

            int j = 0;
            while (j++ < curSize) {
                tail.next = new ListNode(cur.val);
                tail = tail.next;
                cur = cur.next;
            }

            res[i] = dummy.next;
        }

        return res;
    }
}
