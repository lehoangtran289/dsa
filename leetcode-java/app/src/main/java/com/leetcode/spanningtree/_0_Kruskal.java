package com.leetcode.spanningtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _0_Kruskal {
    public static void main(String[] args) {
        int n = 5; // Number of nodes
        int[][] edges = {{0, 1, 1}, {1, 2, 2}, {2, 3, 3}, {0, 3, 4}, {0, 4, 5}, {3, 4, 6}}; // Edges: {u, v, weight}

        // Sort edges by weight
        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));

        // Create disjoint sets
        _0_DisjointSets ds = new _0_DisjointSets(n);

        // Keep track of the edges in the MST
        List<int[]> mst = new ArrayList<>();

        // Iterate through the edges in increasing order of weight
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            // Check if the nodes u and v are connected
            if (ds.find(u) != ds.find(v)) {
                // Add the edge to the MST
                mst.add(edge);

                // Union the sets u and v belong to
                ds.union(u, v);
            }
        }

        // Print the edges in the MST
        for (int[] edge : mst) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
