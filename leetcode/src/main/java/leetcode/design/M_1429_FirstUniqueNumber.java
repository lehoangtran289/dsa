package leetcode.design;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class M_1429_FirstUniqueNumber {
    private final Queue<Integer> numberQueue;
    private final HashMap<Integer, Boolean> isUniqueMap;

    public M_1429_FirstUniqueNumber(int[] nums) {
        numberQueue = new ArrayDeque<>();
        isUniqueMap = new HashMap<>();

        for (int num : nums) {
            this.add(num);
        }
    }

    public int showFirstUnique() {
        while (
                !numberQueue.isEmpty() &&
                isUniqueMap.containsKey(numberQueue.peek()) &&
                !isUniqueMap.get(numberQueue.peek())
        ) {
            numberQueue.poll();
        }

        return numberQueue.isEmpty() ? -1 : numberQueue.peek();
    }

    public void add(int value) {
        if (isUniqueMap.containsKey(value)) {
            isUniqueMap.put(value, false);
        } else {
            isUniqueMap.put(value, true);
            numberQueue.add(value);
        }
    }
}
