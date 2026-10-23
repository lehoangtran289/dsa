package leetcode.design;

import java.util.LinkedList;

/**
 * HashSet = HashMap<Integer, default PRESENT object>
 */
public class E_705_DesignHashSet {

    private final HashTable<Integer, Object> hashTable;
    private final Object PRESENT;

    public E_705_DesignHashSet() {
        this.hashTable = new HashTable<>();
        this.PRESENT = new Object();
    }

    public void add(int key) {
        hashTable.put(key, PRESENT);
    }

    public void remove(int key) {
        hashTable.remove(key);
    }

    public boolean contains(int key) {
        return hashTable.contains(key);
    }

    // ----------------------------------------------------

    static class HashTable<K, V> {
        private final LinkedList<Entry<K, V>>[] table;
        private final int mask;

        public HashTable() {
            this.table = new LinkedList[1 << 10];
            this.mask = table.length - 1;
        }

        public void put(K key, V value) {
            int index = getIndex(key);

            if (table[index] == null) {
                table[index] = new LinkedList<>();
                table[index].add(new Entry<>(key, value));
                return;
            }

            for (var entry : table[index]) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }
            table[index].add(new Entry<>(key, value));
        }

        public V get(K key) {
            int index = getIndex(key);
            if (table[index] == null) return null;

            for (var entry : table[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
            return null;
        }

        public void remove(K key) {
            int index = getIndex(key);
            if (table[index] == null) return;

            for (var entry : table[index]) {
                if (entry.key.equals(key)) {
                    table[index].remove(entry);
                    return;
                }
            }
        }

        public boolean contains(K key) {
            int index = getIndex(key);
            if (table[index] == null) return false;

            for (var entry : table[index]) {
                if (entry.key.equals(key)) return true;
            }
            return false;
        }

        private int getIndex(K key) {
            return key.hashCode() & mask;
        }
    }

    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key + " " + value;
        }
    }
}
