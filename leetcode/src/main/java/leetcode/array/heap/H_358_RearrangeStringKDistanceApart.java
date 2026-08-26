package leetcode.array.heap;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class H_358_RearrangeStringKDistanceApart {
    static void main() {
        System.out.println(rearrangeString("aabbcc", 3)); // "acbacb"
    }

    /**
     * Idea: build a freq array and put to maxHeap
     *      greedily poll from maxHeap to add to result
     *      maintain a queue of size k to represent window of size k, if can poll -> re-insert to maxHeap
     * ---
     * TC: O( (N + K) logK), N be the size of s, K be unique characters
     * SC: O(K)
     */
    public static String rearrangeString(String s, int k) {
        // build frequencies mapping -> O(K)
        int[] frequencies = new int[26];
        for (char c : s.toCharArray()) {
            frequencies[c - 'a']++;
        }

        // create max heap of [character, its frequency] -> O(K logK)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 26; ++i) {
            if (frequencies[i] != 0) maxHeap.add(new int[]{i, frequencies[i]});
        }

        // cooling queue [char, it frequency]
        Queue<int[]> coolingQueue = new ArrayDeque<>();

        StringBuilder res = new StringBuilder();
        while (!maxHeap.isEmpty()) { // run N times -> O(N logK)
            int[] cur = maxHeap.poll();

            // append to result and add to cooling queue (even if freq = 0)
            res.append((char) (cur[0] + 'a'));
            cur[1]--;

            coolingQueue.add(cur);

            // add back to heap after k positions passed
            if (coolingQueue.size() >= k) {
                int[] release = coolingQueue.poll();
                if (release[1] > 0) maxHeap.add(release);
            }
        }

        return res.length() < s.length() ? "" : res.toString();
    }
}
