package com.leetcode.graphtheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class _0_Dijkstra {
    public static void dijkstra(int[][] graph, int s) {
        int n = graph.length;
        boolean[] visitedVertex = new boolean[n];
        int[] distance = new int[n];
        int[] prev = new int[n];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        distance[s] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(s);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            visitedVertex[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visitedVertex[v]) { // if edge (u, v) exists
                    int newDist = distance[u] + graph[u][v];
                    if (newDist < distance[v]) {
                        distance[v] = newDist;
                        prev[v] = u;
                        pq.offer(v);
                    }
                }
            }
        }

        // print the path
        System.out.println("Shortest distances from node " + s + ":");
        for (int i = 0; i < n; i++) {
            if (i == s) continue;
            System.out.println("\nTo node " + i + ": " + distance[i]);
            ArrayList<Integer> path = new ArrayList<>();
            int cur = i;
            while (cur != -1) {
                path.add(cur);
                cur = prev[cur];
            }
            Collections.reverse(path);
            System.out.println("Path: " + path);
            System.out.println("Distance: " + distance[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 4, 4, 0, 0, 0},
                {4, 0, 2, 3, 6, 1},
                {4, 2, 0, 0, 0, 0},
                {0, 3, 0, 0, 2, 0},
                {0, 6, 0, 2, 0, 3},
                {0, 1, 0, 0, 3, 0}
        };
        dijkstra(graph, 0);
    }
}


