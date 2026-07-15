package leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class M_380_InsertDeleteGetRandomO1 {
    private final Random random;
    private final List<Integer> valueList;
    private final Map<Integer, Integer> valueToIndex;

    public M_380_InsertDeleteGetRandomO1() {
        this.random = new Random();
        this.valueList = new ArrayList<>();
        this.valueToIndex = new HashMap<>();
    }

    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) return false;

        valueToIndex.put(val, valueList.size());
        valueList.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) return false;

        // replace val with last element in array
        int idx = valueToIndex.get(val);
        int swapVal = valueList.getLast();

        valueList.set(idx, swapVal);
        valueToIndex.put(swapVal, idx);

        valueToIndex.remove(val);
        valueList.removeLast();

        return true;
    }

    public int getRandom() {
        return valueList.get(random.nextInt(valueList.size()));
    }
}
