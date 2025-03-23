package leetcode.graph.dsu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The weight of the path is the bitwise AND of all edges in the path.
 * <p>
 * The problem states that we should find the smallest weight path between two nodes.
 * => Smallest weight = need as much AND as possible (AND turns off bits) = path with most edges (longest edge path)
 * <p>
 * => The problem is equivalent to find groups of nodes connected together, then calculate AND
 */
public class H_3108_MinimumCostWalkInWeightedGraph {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(minimumCost(
                4,
                new int[][]{{2, 3, 1}, {1, 3, 5}, {1, 2, 6}, {3, 0, 7}, {1, 3, 7}, {0, 2, 5}, {0, 1, 7}},
                new int[][]{{1, 2}, {2, 1}}
        )));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Union Find (Disjoint Set) Approach
    // -----------------------------------------------------------------------------------------------------------------

    public static int[] minimumCost(int n, int[][] edges, int[][] query) {
        // preprocess graph
        DisjointSet ds = new DisjointSet(n);
        Map<Integer, Integer> groups = new HashMap<>();

        for (int[] e : edges) {
            ds.union(e[0], e[1]);
        }
        for (int[] e : edges) {
            int groupId = ds.find(e[0]);
            groups.put(groupId, groups.getOrDefault(groupId, e[2]) & e[2]);
        }

        // process query
        int[] res = new int[query.length];
        for (int i = 0; i < query.length; ++i) {
            int start = query[i][0];
            int end = query[i][1];
            if (ds.find(start) != ds.find(end)) {
                res[i] = -1;
            } else {
                res[i] = groups.get(ds.find(start));
            }
        }

        return res;
    }

    /**
     * DisjointSet to compressed nodes in a group to 1 root
     */
    static class DisjointSet {
        private final int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];

            // Initially, each element is its own set, and the rank is 0
            for (int v = 0; v < n; v++) {
                parent[v] = v;
                rank[v] = 0;
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
                return;
            }
            parent[rootV] = rootU;
            if (rank[rootU] == rank[rootV])
                rank[rootV]++;
        }
    }
}
