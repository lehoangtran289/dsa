package leetcode.spanningtree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class _1584_MinCostToConnectAllPoints {
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
        edges.sort(Comparator.comparingInt(e -> e.weight));

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
