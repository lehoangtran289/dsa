package leetcode.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class M_3650_MinimumCostPathWithEdgeReversals {

    /**
     * Dijkstra
     * ------------------------
     * Time: O(E log V)
     * Space: O(V + E)
     */
    public int minCost(int n, int[][] edges) {
        final int MAX = 1 << 30;

        List<Edge>[] graph = new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int src = edge[0], node = edge[1], cost = edge[2];

            graph[src].add(new Edge(node, cost));
            graph[node].add(new Edge(src, cost * 2));
        }

        int[] dist = new int[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));

        Arrays.fill(dist, MAX);
        dist[0] = 0;
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int node = edge.node;
            int cost = edge.cost;

            if (node == n - 1) return cost;
            if (dist[node] < cost) continue;

            for (Edge neighbor : graph[node]) {
                int nextCost = cost + neighbor.cost;

                if (nextCost < dist[neighbor.node]) {
                    dist[neighbor.node] = nextCost;
                    pq.add(new Edge(neighbor.node, nextCost));
                }
            }
        }

        return dist[n - 1] == MAX ? -1 : dist[n - 1];
    }

    static class Edge {
        int node;
        int cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
