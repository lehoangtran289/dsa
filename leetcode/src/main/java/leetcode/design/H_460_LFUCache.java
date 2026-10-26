package leetcode.design;

import java.util.HashMap;
import java.util.Map;

public class H_460_LFUCache {

    /**
     * Intuition idea:
     * To store key -> (value, freq) => use a map {key -> Entry}
     * To maintain Entry sorted by freq => use min heap
     * Entry(key, value, freq, timestamp)
     * How to delete from min heap? use a Set<Entry> removed to delete from heap lazily
     * But Using heap -> TC is O(log N) amortized > O(1)
     * -> This solution is not optimal
     * -------
     * Idea 2:
     * 1. To maintain key -> (value, freq) => Use a map {key -> Node}
     * 2. To invalidation cache ~ remove min freq node
     *      - Tip: Group same frequency together, store list of Entries with same frequency using doubly linkedlist for O(1) removal and maintaining recently used list.
     => Use another map {frequency -> DLL}
     * DLL = (Node head, Node tail)
     * Keep track of min frequency so we can track least recently used, least frequency node to remove if size > capacity
     */

    private final Map<Integer, Node> keyNodeMap;
    private final Map<Integer, DoublyLinkedList> frequencyListMap;
    private final int capacity;
    private int minFrequency;

    public H_460_LFUCache(int capacity) {
        this.keyNodeMap = new HashMap<>();
        this.frequencyListMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) return -1;
        Node curNode = keyNodeMap.get(key);
        this.addFrequency(curNode);
        return curNode.value;
    }

    public void put(int key, int value) {
        // case 1: key exist in map -> update value & frequency
        if (keyNodeMap.containsKey(key)) {
            Node curNode = keyNodeMap.get(key);
            curNode.value = value;
            this.addFrequency(curNode);
            return;
        }

        // case 2: key not exist in map -> add new node and check capacity
        Node newNode = new Node(key, value);
        keyNodeMap.put(key, newNode);
        this.addToFrequencyMap(newNode);

        if (keyNodeMap.size() > capacity) {
            DoublyLinkedList curFreqList = frequencyListMap.get(minFrequency);
            Node removeNode = curFreqList.removeLast();
            keyNodeMap.remove(removeNode.key);
        }

        // add new node -> min frequency = 1
        minFrequency = 1;
    }

    private void addFrequency(Node curNode) {
        // remove curNode from current frequency list
        DoublyLinkedList curFreqList = frequencyListMap.get(curNode.frequency);
        curFreqList.remove(curNode);

        // update min frequency
        if (curFreqList.isEmpty()) {
            frequencyListMap.remove(curNode.frequency);
            if (curNode.frequency == minFrequency) {
                minFrequency++;
            }
        }

        // add node to next frequency list
        curNode.frequency++;
        this.addToFrequencyMap(curNode);
    }

    private void addToFrequencyMap(Node node) {
        frequencyListMap.putIfAbsent(node.frequency, new DoublyLinkedList());
        frequencyListMap.get(node.frequency).addHead(node);
    }

    // -----

    static class DoublyLinkedList { // same as LRU approach
        Node head;
        Node tail;

        public DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public void addHead(Node node) {
            node.next = head.next;
            node.prev = head;
            node.next.prev = node;
            head.next = node;
        }

        public Node removeLast() {
            Node lastNode = tail.prev;
            remove(tail.prev);
            return lastNode;
        }

        public boolean isEmpty() {
            return head.next == tail;
        }
    }

    static class Node {
        Node prev;
        Node next;
        int key;
        int value;
        int frequency;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }
}
