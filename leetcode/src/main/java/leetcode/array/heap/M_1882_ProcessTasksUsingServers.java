package leetcode.array.heap;

import java.util.PriorityQueue;

public class M_1882_ProcessTasksUsingServers {

    /**
     * Idea: maintain 2 heaps.
     *       + One minHeap of available servers, sorted by server's weight ASC and index ASC
     *       + One minHeap of processing servers with finishing time.
     *
     * Initially add all servers into the heap
     * Traverse all tasks as a task queue and maintain a variable timestamp
     *      check the processing servers heap, if top's finishing time < timestamp -> pop and add to available servers
     *      if minHeap is not empty, assign task[j] to server and move the state [server, finish time] to another min heap
     *      if minHeap is empty -> forward time by popping first processing servers heap
     * ---
     * TC: O((T + S)logS)
     * SC: O(S)
     */
    public int[] assignTasks(int[] servers, int[] tasks) {
        // min heap to store [server index]
        PriorityQueue<Integer> availableServers = new PriorityQueue<>((a, b) ->
                servers[a] == servers[b] ? Integer.compare(a, b) : Integer.compare(servers[a], servers[b])
        );
        for (int i = 0; i < servers.length; ++i) availableServers.add(i);

        // min heap to store processing servers [server index, finishing time]
        PriorityQueue<int[]> processingServers = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        int[] res = new int[tasks.length];
        int resIndex = 0;
        int currentTime = 0;

        for (int j = 0; j < tasks.length; ++j) {
            currentTime = Math.max(currentTime, j); // avoid blindly currentTime++

            // if no server available -> forward time and free finished servers
            if (availableServers.isEmpty()) {
                currentTime = processingServers.peek()[1];
            }
            while (!processingServers.isEmpty() && processingServers.peek()[1] == currentTime) {
                int serverId = processingServers.poll()[0];
                availableServers.add(serverId);
            }

            // assign task to server at this current time being
            int serverId = availableServers.poll();
            processingServers.add(new int[]{serverId, currentTime + tasks[j]});
            res[resIndex++] = serverId;
        }

        return res;
    }
}
