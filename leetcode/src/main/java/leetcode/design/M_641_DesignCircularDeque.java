package leetcode.design;

public class M_641_DesignCircularDeque {

    private final int capacity;
    private int size;
    private ListNode head;
    private ListNode tail;

    /**
     * Deque implementation using doubly linked list
     * -----------------------
     *  null <- head <-> ... <-> tail -> null
     */
    public M_641_DesignCircularDeque(int k) {
        this.capacity = k;
        this.size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) return false;

        if (head == null) {
            head = new ListNode(value);
            tail = head;
        } else {
            ListNode node = new ListNode(value);
            head.next = node;
            node.prev = head;
            head = node;
        }
        size++;

        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) return false;

        if (tail == null) {
            tail = new ListNode(value);
            head = tail;
        } else {
            ListNode node = new ListNode(value);
            tail.prev = node;
            node.next = tail;
            tail = node;
        }
        size++;

        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) return false;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.prev;
            head.next = null;
        }
        size--;

        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) return false;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.next;
            tail.prev = null;
        }
        size--;

        return true;
    }

    public int getFront() {
        if (isEmpty()) return -1;
        return head.val;
    }

    public int getRear() {
        if (isEmpty()) return -1;
        return tail.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next, ListNode prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }
}
