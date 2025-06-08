package leetcode.array.heap;

import java.util.*;

public class M_3170_LexicographicallyMinimumStringAfterRemovingStars {
    public static void main(String[] args) {
        System.out.println(clearStars("aaba*")); // Output: "aab"
    }

    /**
     * Approach: min heap to sort by lexicographical and index
     * -----------------
     * TC: O(logn + n) = O(n)
     * SC: O(n)
     */
    public static String clearStars(String s) {
        // min heap to sort by lexicographical and index
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            else return a[0] - b[0];
        });

        // process char in s, removed character is set to '*'
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '*' && !minHeap.isEmpty()) {
                int[] curMin = minHeap.poll();
                arr[curMin[1]] = '*';
            } else {
                minHeap.add(new int[]{c - 'a', i});
            }
        }

        // build result from arr, bypass * characters
        StringBuilder res = new StringBuilder();
        for (char c : arr) {
            if (c != '*') res.append(c);
        }
        return res.toString();
    }
}
