package com.leetcode.spanningtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1584_Min_Cost_to_Connect_All_Points {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int[] src = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] dest = points[j];
                int dist = Math.abs(src[0] - dest[0]) + Math.abs(src[1] - dest[1]);
                edges.add(new Edge(i, j, dist));
            }
        }
        List<Edge> mst = kruskal(n, edges);
        return mst.stream().mapToInt(e -> e.weight).sum();
    }

    public static List<Edge> kruskal(int n, List<Edge> edges) {
        List<Edge> result = new ArrayList<>();
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(n);

        for (Edge e : edges) {
            int rootSrc = ds.find(e.src);
            int rootDest = ds.find(e.dest);

            if (rootSrc != rootDest) {
                result.add(e);
                ds.union(rootSrc, rootDest);
            }
        }
        return result;
    }
}
