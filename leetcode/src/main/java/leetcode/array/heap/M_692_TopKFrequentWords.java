package leetcode.array.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class M_692_TopKFrequentWords {
    public static void main(String[] args) {
        System.out.println(topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2)); // Output: ["i", "love"]
    }

    /**
     * Find the k most frequent words in a list of words.
     * -----------
     * * TC: O(n log k) where n is the number of words
     * * SC: O(n) for the frequency map and O(k) for the min-heap
     */
    public static List<String> topKFrequent(String[] words, int k) {
        // count frequency of each word
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        // use a min-heap to keep track of the top k frequent words
        Queue<String> minHeap = new PriorityQueue<>((a, b) -> freq.get(a) == freq.get(b) ?
                b.compareTo(a) :
                freq.get(a) - freq.get(b)
        );

        for (var entry : freq.entrySet()) {
            minHeap.add(entry.getKey());

            // if the size of the heap exceeds k, remove the least frequent word
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // build result from heap and reverse to get correct order
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        Collections.reverse(result);

        return result;
    }
}
