package leetcode.graph.dsu;

import java.util.Arrays;

public class M_1135_ConnectingCitiesWithMinimumCost {

    /**
     * Idea: Kruskal's algorithm - Minimum Spanning Tree
     * -----------------------
     * TC: O(M * logN) ~ sorting edges, M is number of edges
     * SC: O(N) ~ space for DSU
     */
    public static int minimumCost(int n, int[][] connections) {
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);

        DisjointSet dsu = new DisjointSet(n);

        int totalEdges = 0, res = 0;
        for (int[] connection : connections) {
            int rootSrc = dsu.find(connection[0]);
            int rootDest = dsu.find(connection[1]);

            if (rootSrc != rootDest) {
                dsu.union(rootSrc, rootDest);

                // calculate cost
                res += connection[2];
                totalEdges++;
            }
        }

        // N nodes -> N-1 edges if all connected
        return totalEdges != n - 1 ? -1 : res;
    }

    static class DisjointSet {
        private int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];

            for (int v = 1; v <= n; ++v) {
                parent[v] = v;
                rank[v] = 1;
            }
        }

        public int find(int v) {
            return v == parent[v] ? v : (parent[v] = find(parent[v]));
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return;

            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
                rank[rootV] += rank[rootU];
            } else {
                parent[rootV] = rootU;
                rank[rootU] += rank[rootV];
            }
        }
    }
}
