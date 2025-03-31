package leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class H_2551_PutMarblesInBags {
    public static void main(String[] args) {
        System.out.println(putMarbles(new int[]{1, 3, 5, 1}, 2)); // 4
    }

    /**
     * Sorting approach
     * TC: O(NlogN)
     * SC: O(N)
     */
    public static long putMarbles(int[] weights, int k) {
        int n = weights.length;
        int[] pairWeights = new int[n - 1];

        for (int i = 0; i < n - 1; ++i) {
            pairWeights[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(pairWeights);

        // get min score
        long minScore = weights[0] + weights[n - 1];
        for (int i = 0; i < k - 1; ++i) {
            minScore += pairWeights[i];
        }

        // get max score
        long maxScore = weights[0] + weights[n - 1];
        int maxOps = 0;
        int maxIdx = pairWeights.length - 1;

        while (maxOps < k - 1) {
            maxScore += pairWeights[maxIdx];
            maxIdx--;
            maxOps++;
        }

        return maxScore - minScore;
    }

    /**
     * Heap approach
     * TC: O(NlogN)
     * SC: O(N)
     */
    public static long putMarbles2(int[] weights, int k) {
        int n = weights.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n - 1; ++i) {
            minHeap.add(weights[i] + weights[i + 1]);
            maxHeap.add(weights[i] + weights[i + 1]);
        }

        // get min score
        long minScore = weights[0] + weights[n - 1];
        int minOps = 0;
        while (!minHeap.isEmpty() && minOps < k - 1) {
            minScore += minHeap.poll();
            minOps++;
        }

        // get max score
        long maxScore = weights[0] + weights[n - 1];
        int maxOps = 0;
        while (!maxHeap.isEmpty() && maxOps < k - 1) {
            maxScore += maxHeap.poll();
            maxOps++;
        }

        return maxScore - minScore;
    }
}
