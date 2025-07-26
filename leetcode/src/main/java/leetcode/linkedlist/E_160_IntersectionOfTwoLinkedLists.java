package leetcode.linkedlist;

public class E_160_IntersectionOfTwoLinkedLists {

    /**
     * 2 pointers approach with 1 pass.
     * Idea: Init 2 pointers at headA and headB, p1 & p2
     * Makes p1 and p2 travel the same distance, so that p1 & p2 could meet at intersection node (or null node at the end)
     * In this case, if c is intersection length: p1 travels a -> c -> b; p2 travels b -> c -> a
     * ----------------------
     * TC: O(N + M)
     * SC: O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }

        return p1;
    }
}
