package leetcode.linkedlist;

import java.util.PriorityQueue;

public class H_23_MergeKSortedLists {

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
        lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
        lists[2] = new ListNode(2, new ListNode(6));

        ListNode mergedList = mergeKLists(lists);

        // Print merged list
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
    }

    /**
     * K ways merge approach, using min heap
     * -------
     * TC: O(N log K)
     * SC: O(K)
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // init heap state with 0-th pointers
        for (ListNode list : lists) {
            if (list != null) minHeap.add(list);
        }

        ListNode res = new ListNode(-1);
        ListNode cur = res;

        // merge lists
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();

            cur.next = node;
            cur = cur.next;

            if (node.next != null) minHeap.add(node.next);
        }

        return res.next;
    }
}
