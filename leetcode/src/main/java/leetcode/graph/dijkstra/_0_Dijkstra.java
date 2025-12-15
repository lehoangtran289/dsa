package leetcode.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _0_Dijkstra {

    /**
     * Dijkstra's Algorithm to find the shortest path from source to all nodes in a weighted graph
     * with non-negative weights.
     * -----------------
     * TC: O((V + E) lg V) = O(E lg V) for connected graph
     * SC: O(V)
     */
    public static void dijkstra(List<Edge>[] adj, int s) {
        int n = adj.length;

        // init dist (v.d - distance from src) and prev (v.prev - previous node in path)
        int[] dist = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        // min-heap by distance v.d
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.v));

        // init source
        dist[s] = 0;
        pq.offer(new Edge(s, 0));

        while (!pq.isEmpty()) {
            Edge node = pq.poll();
            int u = node.v;

            if (node.w > dist[u]) continue; // skip if a better distance is already found

            for (Edge edge : adj[u]) {
                int v = edge.v;
                int curDist = dist[u] + edge.w;

                // relaxation
                if (curDist < dist[v]) {
                    dist[v] = curDist;
                    prev[v] = u;
                    pq.offer(new Edge(v, curDist));
                }
            }
        }

        // trace all path
        System.out.println("Shortest distances from node " + s + ":");
        for (int i = 0; i < n; i++) {
            if (i == s) continue;
            System.out.println("\nTo node " + i + ": " + dist[i]);

            // construct path
            ArrayList<Integer> path = new ArrayList<>();
            int cur = i;
            while (cur != -1) {
                path.add(cur);
                cur = prev[cur];
            }
            Collections.reverse(path);
            System.out.println("Path: " + path);
            System.out.println("Distance: " + dist[i]);
        }
    }

    public static void main(String[] args) {
        List<Edge>[] graph = new List[6];
        for (int i = 0; i < 6; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(1, 1));
        graph[0].add(new Edge(2, 4));
        graph[1].add(new Edge(0, 1));
        graph[1].add(new Edge(2, 2));
        graph[1].add(new Edge(3, 3));
        graph[1].add(new Edge(4, -6));
        graph[1].add(new Edge(5, 1));
        graph[2].add(new Edge(1, 2));
        graph[2].add(new Edge(0, 4));
        graph[3].add(new Edge(1, 3));
        graph[3].add(new Edge(4, 2));
        graph[4].add(new Edge(1, 6));
        graph[4].add(new Edge(3, 2));
        graph[4].add(new Edge(5, 3));
        graph[5].add(new Edge(1, 1));
        graph[5].add(new Edge(4, 3));

        dijkstra(graph, 0);
    }

    static class Edge {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}


