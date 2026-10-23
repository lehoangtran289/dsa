package leetcode.design;

import java.util.LinkedList;

public class E_706_DesignHashMap {
    static void main() {
        E_706_DesignHashMap hashMap = new E_706_DesignHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1)); // 1
        System.out.println(hashMap.get(3)); // -1
        hashMap.put(2, 1); // update the existing value
        System.out.println(hashMap.get(2)); // 1
        hashMap.remove(2); // remove the mapping for 2
        System.out.println(hashMap.get(2)); // -1
    }

    // -----------------------------------------------------------------------

    private final HashTable<Integer, Integer> hashTable;

    public E_706_DesignHashMap() {
        this.hashTable = new HashTable<>();
    }

    public void put(int key, int value) {
        hashTable.put(key, value);
    }

    public int get(int key) {
        Integer result = hashTable.get(key);
        return result != null ? result : -1;
    }

    public void remove(int key) {
        hashTable.remove(key);
    }

    // Custom hash table -----------------------------------------------

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
    }
}
