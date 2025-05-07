package leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class M_146_LRUCache {
    private final int capacity;
    private final Map<Integer, ListNode> map;
    private final ListNode head;
    private final ListNode tail;

    public M_146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new ListNode(-1, -1); // dummy node head
        this.tail = new ListNode(-1, -1); // dummy node tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        ListNode node = map.get(key);
        remove(node);
        addLast(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            remove(node);
            addLast(node);
        } else {
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            addLast(node);

            if (map.size() > capacity) {
                ListNode cur = head.next;
                remove(cur);
                map.remove(cur.key);
            }
        }
    }

    // ---- UTILS
    private void addLast(ListNode node) {
        ListNode prev = tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        tail.prev = node;
    }

    private void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    static class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
