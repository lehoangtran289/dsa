package leetcode.array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class M_2107P_NumberOfUniqueFlavorsAfterSharingKCandies {
    public static void main(String[] args) {
        System.out.println(shareCandies(new int[]{1, 2, 3, 4, 5}, 2)); // 3
        System.out.println(shareCandies(new int[]{1, 1, 1, 1}, 2)); // 1
    }

    /**
     * Sliding Window + Frequency Array
     * ---
     * TC: O(n) single pass
     * SC: O(m) where m is the max flavor number, in this case m = 1e5
     */
    public static int shareCandies(int[] candies, int k) {
        int[] freqs = new int[(int) 1e5 + 1];
        int size = 0; // To mimic hashmap.size()

        // init map
        for (int candy : candies) {
            if (freqs[candy] == 0) size++;
            freqs[candy]++;
        }

        // process first k
        for (int i = 0; i < k; ++i) {
            freqs[candies[i]]--;
            if (freqs[candies[i]] == 0) size--;
        }
        int res = size;

        // sliding window
        for (int i = k; i < candies.length; ++i) {
            if (freqs[candies[i - k]] == 0) size++;
            freqs[candies[i - k]]++;

            freqs[candies[i]]--;
            if (freqs[candies[i]] == 0) size--;

            res = Math.max(res, size);
        }

        return res;
    }

    /**
     * Sliding Window + HashMap
     * ---
     * TC: O(n) single pass
     * SC: O(m) where m is the max flavor number, in this case m = 1e5
     */
    public static int shareCandies2(int[] candies, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        // init map
        for (int candy : candies) {
            freqMap.put(candy, freqMap.getOrDefault(candy, 0) + 1);
        }

        // process first k
        for (int i = 0; i < k; ++i) {
            freqMap.put(candies[i], freqMap.get(candies[i]) - 1);
            if (freqMap.get(candies[i]) <= 0) freqMap.remove(candies[i]);
        }
        int res = freqMap.size();

        // sliding window
        for (int i = k; i < candies.length; ++i) {
            freqMap.put(candies[i - k], freqMap.getOrDefault(candies[i - k], 0) + 1);

            freqMap.put(candies[i], freqMap.get(candies[i]) - 1);
            if (freqMap.get(candies[i]) <= 0) freqMap.remove(candies[i]);

            res = Math.max(res, freqMap.size());
        }

        return res;
    }
}
