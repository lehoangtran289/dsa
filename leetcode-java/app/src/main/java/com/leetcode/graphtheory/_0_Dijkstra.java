package com.leetcode.graphtheory;

import java.util.*;

public class _0_Dijkstra {
    public static void dijkstra(List<int[]>[] graph, int s) {
        int n = graph.length;
        Set<Integer> visited = new HashSet<>();
        int[] dist = new int[n];
        int[] prev = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[s] = 0;
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(u -> dist[u[0]]));
        pq.offer(new Integer[]{s, 0});

        while (!pq.isEmpty()) {
            Integer[] p = pq.poll();
            int u = p[0];
            visited.add(u);

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int vW = edge[1];
                if (!visited.contains(v)) { // if edge (u, v) exists
                    int tempDist = dist[u] + vW;
                    if (tempDist < dist[v]) {
                        dist[v] = tempDist;
                        prev[v] = u;
                        pq.offer(new Integer[]{v, tempDist});
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
        List<int[]>[] graph = new List[6];
        for (int i = 0; i < 6; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new int[]{1, 1});
        graph[0].add(new int[]{2, 4});
        graph[1].add(new int[]{0, 1});
        graph[1].add(new int[]{2, 2});
        graph[1].add(new int[]{3, 3});
        graph[1].add(new int[]{4, 6});
        graph[1].add(new int[]{5, 1});
        graph[2].add(new int[]{1, 2});
        graph[2].add(new int[]{0, 4});
        graph[3].add(new int[]{1, 3});
        graph[3].add(new int[]{4, 2});
        graph[4].add(new int[]{1, 6});
        graph[4].add(new int[]{3, 2});
        graph[4].add(new int[]{5, 3});
        graph[5].add(new int[]{1, 1});
        graph[5].add(new int[]{4, 3});

        dijkstra(graph, 0);
    }
}


