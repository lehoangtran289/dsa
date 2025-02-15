package leetcode.design;

import java.util.ArrayList;
import java.util.List;

public class M_1756_DesignMostRecentlyUsedQueue {
    /**
     *  become inefficient when READ > WRITE since READ requires element shift O(n)
     */
    static class MRUQueue {
        private List<Integer> list = new ArrayList<>();

        public MRUQueue(int n) {
            for (int i = 1; i <= n; ++i) {
                list.add(i);
            }
        }

        public int fetch(int k) {
            int res = list.get(k - 1);

            list.remove(k - 1);
            list.add(res);

            return res;
        }
    }

    // --------------------------------------

    static class MRUQueueLinkedList {
        private final ListNode head;
        private ListNode tail;

        public MRUQueueLinkedList(int n) {
            head = new ListNode(0);
            ListNode cur = head;

            for (int i = 1; i <= n; ++i) {
                cur.next = new ListNode(i);
                cur = cur.next;
            }

            tail = cur;
        }

        public int fetch(int k) {
            ListNode cur = head;
            for (int i = 1; i < k - 1; ++i) {
                cur = cur.next;
            }

            int val = cur.next.val;

            tail.next = new ListNode(val);
            tail = tail.next;
            cur.next = cur.next.next;

            return val;
        }

        private void print() {
            ListNode cur = head;
            while (cur != null) {
                System.out.print(cur.val + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}
