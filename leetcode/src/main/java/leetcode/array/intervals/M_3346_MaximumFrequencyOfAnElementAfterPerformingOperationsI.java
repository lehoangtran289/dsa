package leetcode.array.intervals;

import java.util.*;

public class M_3346_MaximumFrequencyOfAnElementAfterPerformingOperationsI {

    /**
     * Line sweep + difference array using TreeMap
     * -----------------
     * TC: O(n)
     * SC: O(n)
     */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(); // for line sweep

        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            int start = num - k;
            int end = num + k + 1;

            // Mark the position of the original number (with 0 increment initially)
            sortedMap.putIfAbsent(num, 0);

            // Build the difference array
            sortedMap.put(start, sortedMap.getOrDefault(start, 0) + 1);
            sortedMap.put(end, sortedMap.getOrDefault(end, 0) - 1);
        }

        int res = 0;
        int curFreq = 0;

        // difference array technique
        for (var entry : sortedMap.entrySet()) {
            int num = entry.getKey();
            int value = entry.getValue();

            curFreq += value;

            // curFreq is the total number of elements that can reach 'num'
            // But we can only modify 'numOperations' elements
            int validFreq = Math.min(
                    curFreq,
                    freqMap.getOrDefault(num, 0) + numOperations
            );

            res = Math.max(res, validFreq);
        }

        return res;
    }
}
