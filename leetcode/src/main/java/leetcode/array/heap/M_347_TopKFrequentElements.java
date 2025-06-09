package leetcode.array.heap;

import java.util.*;

public class M_347_TopKFrequentElements {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2))); // Output: [1, 2]
    }

    /**
     * Find the k most frequent elements in an array.
     * ---------
     * * TC: O(n log k) where n is the number of elements in the array
     * * SC: O(n) for the frequency map and O(k) for the min-heap
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // count frequency of each number
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // use a min-heap to keep track of the top k frequent elements
        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> freq.get(a) - freq.get(b));
        for (var entry : freq.entrySet()) {
            minHeap.add(entry.getKey());

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // build result from heap
        int[] res = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            res[i++] = minHeap.poll();
        }

        return res;
    }
}
