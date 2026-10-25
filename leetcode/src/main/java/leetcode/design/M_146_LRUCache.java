package leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class M_146_LRUCache {
    private final Map<Integer, Node> keyNodeMap;
    private final Node head;
    private final Node tail;
    private final int capacity;

    public M_146_LRUCache(int capacity) {
        this.keyNodeMap = new HashMap<>();

        // set up boundary nodes (avoid null checks)
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;

        this.capacity = capacity;
    }

    /**
     * If key exists -> return key and move node to head
     * Else return -1
     */
    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) return -1;

        Node currentNode = keyNodeMap.get(key);
        removeNode(currentNode);
        addHead(currentNode);
        return currentNode.val;
    }

    /**
     * If key exists, update value & put node to head;
     * Else add new node and entry in map, check cacheSize
     *      If curSize > capacity -> evict tail node and entry in hashmap
     */
    public void put(int key, int value) {
        // case 1: key already exist
        if (keyNodeMap.containsKey(key)) {
            Node currentNode = keyNodeMap.get(key);
            currentNode.val = value;
            removeNode(currentNode);
            addHead(currentNode);
            return;
        }

        // case 2: key not exist
        Node newNode = new Node(key, value);
        keyNodeMap.put(key, newNode);
        addHead(newNode);

        if (keyNodeMap.size() > capacity) {
            Node tailNode = tail.prev;
            keyNodeMap.remove(tailNode.key);
            removeNode(tailNode);
        }
    }

    // --- HELPERS

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    static class Node {
        Node prev;
        Node next;
        int key;
        int val;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
