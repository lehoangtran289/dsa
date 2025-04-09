package leetcode.linkedlist;

public class E_206_ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(head);
        ListNode reversedHead = reverseList(head);
        System.out.println(reversedHead);
    }

    /**
     * Iterative approach
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }

        return prev;
    }

    /**
     * Recursive approach
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode temp = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }
}
