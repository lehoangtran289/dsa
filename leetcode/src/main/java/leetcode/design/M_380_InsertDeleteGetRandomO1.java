package leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class M_380_InsertDeleteGetRandomO1 {
    static class RandomizedSet {
        private final List<Integer> arr;
        private final Map<Integer, Integer> map;

        public RandomizedSet() {
            arr = new ArrayList<>();
            map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) return false;

            map.put(val, arr.size());
            arr.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;

            // replace val with last element in array
            int curIdx = map.get(val);
            int lastVal = arr.get(arr.size() - 1);

            arr.set(curIdx, lastVal);
            map.put(lastVal, curIdx);

            map.remove(val);
            arr.remove(arr.size() - 1);

            return true;
        }

        public int getRandom() {
            int idx = ThreadLocalRandom.current().nextInt(arr.size()) % arr.size();
            return arr.get(idx);
        }
    }
}
