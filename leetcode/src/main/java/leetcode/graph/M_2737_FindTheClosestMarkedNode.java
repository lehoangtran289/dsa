package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class M_2737_FindTheClosestMarkedNode {
    static class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        List<Edge>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (List<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);
            int weight = edge.get(2);
            adj[from].add(new Edge(to, weight));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Edge(s, 0));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (Edge neighbor : adj[cur.node]) {
                int tempDist = dist[cur.node] + neighbor.weight;
                if (tempDist < dist[neighbor.node]) {
                    dist[neighbor.node] = tempDist;
                    pq.add(new Edge(neighbor.node, tempDist));
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int node : marked) {
            res = Math.min(res, dist[node]);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
