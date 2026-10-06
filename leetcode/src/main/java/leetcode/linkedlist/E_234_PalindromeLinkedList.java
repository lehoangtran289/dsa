package leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class E_234_PalindromeLinkedList {

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public boolean isPalindrome(ListNode head) {
        // 1. find the middle node using fast-slow pointers
        ListNode slow = head, fast = head;

        // odd  0 (1) 2    -> slow = mid node
        // even 0  1 (2) 3 -> slow = n / 2 node
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. reverse the second half
        ListNode prev = null, cur = slow;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // 3. check palindrome
        ListNode p1 = head, p2 = prev;
        while (p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    /**
     * TC: O(n)
     * SC: O(n)
     */
    public boolean isPalindrome2(ListNode head) {
        List<Integer> list = new ArrayList<>();

        ListNode ptr = head;
        while (ptr != null) {
            list.add(ptr.val);
            ptr = ptr.next;
        }

        int i = list.size() - 1;
        while (head != null) {
            if (head.val != list.get(i--)) return false;
            head = head.next;
        }
        return true;
    }
}
