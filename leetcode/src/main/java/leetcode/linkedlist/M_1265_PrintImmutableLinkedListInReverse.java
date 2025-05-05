package leetcode.linkedlist;

public class M_1265_PrintImmutableLinkedListInReverse {
    interface ImmutableListNode {
        ImmutableListNode getNext();
        void printValue();
    }

    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head != null) {
            printLinkedListInReverse(head.getNext());
            head.printValue();
        }
    }
}
