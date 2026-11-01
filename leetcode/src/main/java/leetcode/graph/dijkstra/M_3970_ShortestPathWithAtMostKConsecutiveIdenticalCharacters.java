package leetcode.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class M_3970_ShortestPathWithAtMostKConsecutiveIdenticalCharacters {

    /**
     * Idea: maintain consecutive run in dijkstra states. If s[curNode] == s[nextNode] -> count++, else count = 1
     * ---
     * TC: O(n + m + nk + mklog(mk)), m = #edges
     * SC: O(n + m + nk + mk), nk = dist size, mk = heap size
     */
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        final int INF = 1 << 30;

        // build graph -> O(n + #edges)
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            graph[from].add(new int[]{to, weight});
        }

        // init dijkstra states
        // min heap state => [next node, current weight, current consecutive run]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) Arrays.fill(row, INF); // O(n * k)

        minHeap.add(new int[]{0, 0, 1});
        dist[0][1] = 0;

        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int curNode = cur[0], curDist = cur[1], curCount = cur[2];

            // pruning
            if (curNode == n - 1) return curDist;
            if (curDist > dist[curNode][curCount]) continue;

            for (int[] next : graph[curNode]) {
                int nextNode = next[0], nextWeight = next[1];

                int nextDist = curDist + nextWeight;
                int nextCount = labels.charAt(curNode) == labels.charAt(nextNode) ? curCount + 1 : 1;

                // relax edges
                if (nextCount <= k && nextDist < dist[nextNode][nextCount]) {
                    dist[nextNode][nextCount] = nextDist;
                    minHeap.add(new int[]{nextNode, nextDist, nextCount});
                }
            }
        }

        return -1;
    }
}
