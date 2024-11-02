package leetcode.linkedlist;

public class E_21_MergeTwoSortedList {
    public static void main(String[] args) {
        System.out.println(new E_21_MergeTwoSortedList().mergeTwoLists(
                new ListNode(-9, new ListNode(3)),
                new ListNode(5, new ListNode(7))
        ));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode();
        ListNode temp = res;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        if (list1 != null) {
            temp.next = list1;
        }
        if (list2 != null) {
            temp.next = list2;
        }
        return res.next;
    }
}
