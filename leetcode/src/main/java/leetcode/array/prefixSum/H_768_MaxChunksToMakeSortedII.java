package leetcode.array.prefixSum;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class H_768_MaxChunksToMakeSortedII {

    /**
     * Monotonic stack
     * ---
     * Observation: Max value in chunk (i-1)-th < Min value in chunk i-th
     * As we process each element, we decide if it should forms a new chunk or merge to previous chunk
     * If current value > prev max -> forms new chunk
     * Else we need to find prev max that < current value to merge it to chunk
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public int maxChunksToSorted3(int[] arr) {
        Deque<Integer> incrStack = new ArrayDeque<>();

        for (int currentValue : arr) {
            // if current value > prev max -> make a chunk by pushing to stack
            if (incrStack.isEmpty() || incrStack.peek() <= currentValue) {
                incrStack.push(currentValue);
                continue;
            }

            // store current max value
            int currentMax = incrStack.pop();

            // find corresponding chunk of current value
            while (!incrStack.isEmpty() && incrStack.peek() > currentValue) {
                incrStack.pop();
            }
            incrStack.push(currentMax);
        }

        return incrStack.size();
    }

    /**
     * Same solution as M_769
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];

        prefixMax[0] = arr[0];
        suffixMin[n - 1] = arr[n - 1];

        for (int i = 1; i < n; ++i) {
            prefixMax[i] = Math.max(prefixMax[i - 1], arr[i]);
            suffixMin[n - 1 - i] = Math.min(suffixMin[n - i], arr[n - 1 - i]);
        }

        int res = 1;
        for (int i = 0; i < n - 1; ++i) {
            if (prefixMax[i] <= suffixMin[i + 1]) res++;
        }
        return res;
    }

    /**
     * Intuition idea: If a permutation of arr[:k] matches sort(arr)[:k] -> make a chunk
     * ---
     * TC: O(N^2)
     * SC: O(N)
     */
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int res = 0;
        int[] sortedArr = arr.clone();

        Arrays.sort(sortedArr);

        Map<Integer, Integer> sortedFrequencies = new HashMap<>();
        Map<Integer, Integer> originalFrequencies = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            sortedFrequencies.put(sortedArr[i], sortedFrequencies.getOrDefault(sortedArr[i], 0) + 1);
            originalFrequencies.put(arr[i], originalFrequencies.getOrDefault(arr[i], 0) + 1);

            if (sortedFrequencies.equals(originalFrequencies)) {
                res++;
            }
        }
        return res;
    }
}
