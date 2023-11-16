package com.leetcode.graphtheory;

import java.util.Arrays;

public class _0_Floyd_Warshall {
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 5, Integer.MAX_VALUE, 10},
                {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        System.out.println(Arrays.deepToString(floydWarshall(graph)));
    }

    public static int[][] floydWarshall(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];

        // init dist
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++)
                dist[u][v] = graph[u][v];
        }

        // compute all shortest paths
        for (int k = 0; k < n; k++) // k is intermediate vertex
            for (int u = 0; u < n; u++)
                for (int v = 0; v < n; v++)
                    if (dist[u][k] != Integer.MAX_VALUE && dist[k][v] != Integer.MAX_VALUE
                            && dist[u][k] + dist[k][v] < dist[u][v])
                        dist[u][v] = dist[u][k] + dist[k][v];
        return dist;
    }
}
