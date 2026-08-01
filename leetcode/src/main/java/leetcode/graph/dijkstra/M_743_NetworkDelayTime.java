package leetcode.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class M_743_NetworkDelayTime {
    static void main() {
        System.out.println(
                networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2)
        ); // Output: 2
    }

    /**
     * TC: O(E log V) where E is the number of edges and V is the number of vertices
     * SC: O(V + E) for the adjacency list and distance array
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        // build adj list
        List<int[]>[] adjList = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            adjList[time[0]].add(new int[]{time[1], time[2]}); // [src -> [dst, dist]]
        }

        // set up djkstra states
        int[] dist = new int[n + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1])); // [dst, curDist]

        Arrays.fill(dist, 1 << 30);
        dist[k] = 0;
        minHeap.add(new int[]{k, 0});

        // dijkstra process
        while (!minHeap.isEmpty()) {
            int[] node = minHeap.poll();
            int curDist = node[1];

            for (int[] neighbor : adjList[node[0]]) {
                int dst = neighbor[0];
                int nextDist = curDist + neighbor[1];

                if (nextDist < dist[dst]) {
                    dist[dst] = nextDist;
                    minHeap.add(new int[]{dst, nextDist});
                }
            }
        }

        // build result
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            if (dist[i] == 1 << 30) return -1;
            res = Math.max(res, dist[i]);
        }
        return res;
    }
}
