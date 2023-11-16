package com.leetcode.graphtheory;

import java.util.*;

public class _0_Dijkstra {
    static class Edge {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void dijkstra(List<Edge>[] graph, int s) {
        int n = graph.length;
        int[] dist = new int[n];
        int[] prev = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[s] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.v));
        pq.offer(new Edge(s, 0));

        while (!pq.isEmpty()) {
            Edge node = pq.poll();
            int u = node.v;

            for (Edge edge : graph[u]) {
                int v = edge.v;
                int vW = edge.w;
                int tempDist = dist[u] + vW;
                if (tempDist < dist[v]) {
                    dist[v] = tempDist;
                    prev[v] = u;
                    pq.offer(new Edge(v, tempDist));
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
        graph[1].add(new Edge(4, 6));
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
}


