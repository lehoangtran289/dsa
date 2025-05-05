package leetcode.linkedlist;

public class M_2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;
            int sum = n1 + n2 + carry;

            carry = sum / 10;
            int n3 = sum % 10;
            cur.next = new ListNode(n3);
            cur = cur.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return res.next;
    }
}
