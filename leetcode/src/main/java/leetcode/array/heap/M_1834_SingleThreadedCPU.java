package leetcode.array.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M_1834_SingleThreadedCPU {

    /**
     * Sorting (enqueueTime) + Min Heap (processingTime)
     * ---------------------------
     * TC: O(n log n)
     * SC: O(n)
     */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        // add index to tasks and sort by enqueueTime
        for (int i = 0; i < n; ++i) {
            int[] taskInfo = new int[3];
            taskInfo[0] = i;             // index
            taskInfo[1] = tasks[i][0];  // enqueueTime
            taskInfo[2] = tasks[i][1];  // processingTime
            tasks[i] = taskInfo;
        }
        Arrays.sort(tasks, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        // <index, enqueueTime, processingTime>
        // Store available tasks sorted by processingTime, then by index
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) ->
                a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]
        );

        int[] res = new int[n];
        int resIndex = 0;
        int curIndex = 0;
        int curTime = 0;
        int completedTasks = 0;

        while (completedTasks < n) {
            // add all available tasks to the minHeap
            for (int i = curIndex; i < n; ++i) {
                if (tasks[i][1] > curTime) break;

                minHeap.add(tasks[i]);
                curIndex++;
            }

            // if no available tasks, jump to the next task's enqueueTime
            if (minHeap.isEmpty()) {
                curTime = tasks[curIndex][1];
                continue;
            }

            // process the next task
            int[] curTask = minHeap.poll();
            curTime += curTask[2];
            res[resIndex++] = curTask[0];
            completedTasks++;
        }

        return res;
    }
}
