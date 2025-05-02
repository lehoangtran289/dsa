package leetcode.design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E_706_DesignHashMap {
    static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    static class Bucket {
        private final List<Pair<Integer, Integer>> bucket;

        public Bucket() {
            this.bucket = new LinkedList<>();
        }

        public int get(int key) {
            for (Pair<Integer, Integer> pair : bucket) {
                if (pair.key == key) return pair.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            boolean seen = false;
            for (Pair<Integer, Integer> pair : bucket) {
                if (pair.key == key) {
                    pair.value = value;
                    seen = true;
                }
            }

            if (!seen) {
                bucket.add(new Pair<>(key, value));
            }
        }

        public void remove(int key) {
            for (Pair<Integer, Integer> pair : bucket) {
                if (pair.key == key) {
                    bucket.remove(pair);
                    return;
                }
            }
        }
    }

    // -----------------------------------------------------------------------

    private static final int capacity = 1_000;
    private final List<Bucket> hashMap;

    public E_706_DesignHashMap() {
        this.hashMap = new ArrayList<>();
        for (int i = 0; i < capacity; ++i) {
            hashMap.add(new Bucket());
        }
    }

    public void put(int key, int value) {
        int bucketIndex = hash(key);
        hashMap.get(bucketIndex).put(key, value);
    }

    public int get(int key) {
        int bucketIndex = hash(key);
        return hashMap.get(bucketIndex).get(key);
    }

    public void remove(int key) {
        int bucketIndex = hash(key);
        hashMap.get(bucketIndex).remove(key);
    }

    private int hash(int key) {
        return key % capacity;
    }

    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        E_706_DesignHashMap hashMap = new E_706_DesignHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        System.out.println(hashMap.get(1)); // returns 1
        System.out.println(hashMap.get(3)); // returns -1 (not found)
        hashMap.put(2, 1); // update the existing value
        System.out.println(hashMap.get(2)); // returns 1
        hashMap.remove(2); // remove the mapping for 2
        System.out.println(hashMap.get(2)); // returns -1 (not found)
    }
}
