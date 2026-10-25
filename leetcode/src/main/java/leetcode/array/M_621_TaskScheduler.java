package leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class M_621_TaskScheduler {

    /**
     * Idea: Math,
     * - When distinct tasks > n -> time needed = number of tasks
     * - When distinct tasks < n ->
     *      Consider max frequency task always needs at least (maxFreq - 1) cycles of CPU interval, plus its remaining
     * - Also, 1 cycle = n + 1 tasks
     * -> result = max(number of tasks, (maxFreq - 1) * (n + 1) + maxFreqCount);
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public int leastInterval(char[] tasks, int n) {
        int[] taskFrequencies = new int[26];
        for (char task : tasks) {
            taskFrequencies[task - 'A']++;
        }

        int maxFreq = 0;
        for (int freq : taskFrequencies) {
            maxFreq = Math.max(maxFreq, freq);
        }

        int maxFreqCount = 0;
        for (int freq : taskFrequencies) {
            if (freq == maxFreq) maxFreqCount++;
        }

        return Math.max(
                tasks.length, // when distinct tasks > n
                (maxFreq - 1) * (n + 1) + maxFreqCount
        );
    }

    /**
     * Idea: max frequency task always needs at least maxFreq - 1 cycles of CPU interval, plus remaining
     * ---
     * TC: O(N log26)
     * SC: O(N)
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] taskFrequencies = new int[26];
        for (char task : tasks) {
            taskFrequencies[task - 'A']++;
        }

        // Max heap to store frequencies
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < 26; i++) {
            if (taskFrequencies[i] > 0) maxHeap.add(taskFrequencies[i]);
        }

        int res = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> store = new ArrayList<>();
            int slots = n + 1;
            int taskCount = 0;

            while (!maxHeap.isEmpty() && slots-- > 0) {
                int currentFreq = maxHeap.poll();
                currentFreq--;
                taskCount++;
                store.add(currentFreq);
            }

            for (int freq : store) {
                if (freq > 0) maxHeap.add(freq);
            }
            res += maxHeap.isEmpty() ? taskCount : n + 1;
        }
        return res;
    }
}
