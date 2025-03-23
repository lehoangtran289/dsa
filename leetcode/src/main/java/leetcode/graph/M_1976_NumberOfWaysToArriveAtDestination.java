package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class M_1976_NumberOfWaysToArriveAtDestination {
    public static void main(String[] args) {
        System.out.println(countPaths(7, new int[][]{
                {0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}
        }));
    }

    static class Edge {
        int node;
        long time;

        Edge(int node, long time) {
            this.node = node;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Edge{" +
                   "node=" + node +
                   ", time=" + time +
                   '}';
        }
    }

    /**
     * Dijkstra approach
     */
    public static int countPaths(int n, int[][] roads) {
        final int MOD = (int) (Math.pow(10, 9) + 7);

        List<Edge>[] adj = new List[n];

        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int[] r : roads) {
            adj[r[0]].add(new Edge(r[1], r[2]));
            adj[r[1]].add(new Edge(r[0], r[2]));
        }

        // find min time from 0 to n - 1
        long[] dist = new long[n];
        int[] count = new int[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> (int) (a.time - b.time));

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        count[0] = 1;
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.time > dist[cur.node]) continue;

            for (Edge next : adj[cur.node]) {
                long newDist = dist[cur.node] + next.time;
                if (newDist < dist[next.node]) {
                    dist[next.node] = newDist;
                    count[next.node] = count[cur.node];
                    pq.add(new Edge(next.node, newDist));
                }

                // if this path reach next.node with same weight
                else if (newDist == dist[next.node]) {
                    count[next.node] = (count[cur.node] + count[next.node]) % MOD;
                }
            }
        }

        return count[n - 1];
    }
}
