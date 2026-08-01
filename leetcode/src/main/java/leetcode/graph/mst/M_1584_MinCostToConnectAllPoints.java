package leetcode.graph.mst;

import java.util.ArrayList;
import java.util.List;

public class M_1584_MinCostToConnectAllPoints {

    /**
     * Idea: build edges between all points -> Use Kruskal to find MST
     * ---
     * TC: O(n^2 log n), where n is the number of points
     * SC: O(n^2)
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        DisjointSet dsu = new DisjointSet(n);
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < n; ++i) { // O(n^2)
            for (int j = i + 1; j < n; ++j) {
                int dist = getDist(points[i], points[j]);
                edges.add(new int[]{i, j, dist});
            }
        }

        edges.sort((a, b) -> Integer.compare(a[2], b[2])); // O(n^2 log n)

        int res = 0;
        for (int[] edge : edges) {
            if (dsu.find(edge[0]) != dsu.find(edge[1])) {
                dsu.join(edge[0], edge[1]);
                res += edge[2];
            }
        }
        return res;
    }

    private int getDist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
