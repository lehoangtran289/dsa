package com.leetcode.graphtheory;

import java.util.*;

public class _0_Dijkstra {
    public static void dijkstra(int[][] graph, int s) {
        int n = graph.length;
        Set<Integer> visited = new HashSet<>();
        int[] dist = new int[n];
        int[] prev = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[s] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(u -> dist[u]));
        for (int i = 0; i < n; i++)
            pq.offer(i);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            visited.add(u);

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited.contains(v)) { // if edge (u, v) exists
                    int tempDist = dist[u] + graph[u][v];
                    if (tempDist < dist[v]) {
                        dist[v] = tempDist;
                        prev[v] = u;
                        pq.offer(v);
                    }
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
        int[][] graph = new int[][]{
                {0, 1, 4, 0, 0, 0},
                {1, 0, 2, 3, 6, 1},
                {4, 2, 0, 0, 0, 0},
                {0, 3, 0, 0, 2, 0},
                {0, 6, 0, 2, 0, 3},
                {0, 1, 0, 0, 3, 0}
        };
        dijkstra(graph, 0);
    }
}


