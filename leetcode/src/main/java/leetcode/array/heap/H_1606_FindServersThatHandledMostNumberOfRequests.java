package leetcode.array.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class H_1606_FindServersThatHandledMostNumberOfRequests {

    /**
     * Idea: Maintain treeSet & MinHeap
     T1 (TreeSet - idle servers): [0 1 2]
     Q2 (MinHeap - busy servers & its finishing time): []
     ---
     request 0 (1, 5) -> server 0
     Q1: [1 2]
     Q2: [(0, 6)] -> update counts [1, 0, 0]
     ---
     request 1 (2, 2) -> server 1
     Q1: [2]
     Q2: [(1, 4), (0, 6)] -> update counts [1, 1, 0]
     ---
     request 2 (3, 3) -> server 2
     Q1: []
     Q2: [(2, 6), (1, 4), (0, 6)] -> update counts [1, 1, 1]
     ---
     request 3 (4, 3) -> put server 1 to idle, server 0 (busy? -> HashSet) -> linear probing -> server 1
     Q1: []
     Q2: [(2, 6), (0, 6), (1, 7)] -> update counts [1, 2, 1]
     ---
     request 4 (5, 3) -> check busy queue top -> no server idle -> drop
     ---
     TC: O(NlogN)
     SC: O(N) - number of servers
     */
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] serverWorkCounts = new int[k];
        int maxWorkCount = 0;

        // stores idle servers, allows searching next idle server
        TreeSet<Integer> idleServers = new TreeSet<>();
        for (int i = 0; i < k; ++i) idleServers.add(i);

        // stores processing servers, along with its releasing time -> BusyServer(server id, releasing time)
        PriorityQueue<int[]> busyServersHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int i = 0; i < arrival.length; ++i) {
            int arrivalTime = arrival[i], loadTime = load[i];

            // free all finished servers
            while (!busyServersHeap.isEmpty() && busyServersHeap.peek()[1] <= arrivalTime) {
                idleServers.add(busyServersHeap.poll()[0]);
            }

            // check if there is available idle server to assign new request
            if (idleServers.isEmpty()) continue; // drop request

            Integer idleServer = idleServers.ceiling(i % k);
            if (idleServer == null) idleServer = idleServers.first();

            // assign new request
            idleServers.remove(idleServer);
            serverWorkCounts[idleServer]++;
            maxWorkCount = Math.max(maxWorkCount, serverWorkCounts[idleServer]);
            busyServersHeap.add(new int[]{idleServer, arrivalTime + loadTime});
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            if (serverWorkCounts[i] == maxWorkCount) result.add(i);
        }
        return result;
    }
}
