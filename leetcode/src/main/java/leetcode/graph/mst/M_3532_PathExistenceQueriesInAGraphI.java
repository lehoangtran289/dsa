package leetcode.graph.mst;

import java.util.Arrays;

public class M_3532_PathExistenceQueriesInAGraphI {

    /**
     * Disjoint Set Union (DSU) / Union-Find
     * ---
     * TC: O(n + q) where n is the number of nodes and q is the number of queries
     * SC: O(n)
     */
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DisjointSet dsu = new DisjointSet(n);

        for (int i = 0; i < n - 1; ++i) {
            if (nums[i + 1] - nums[i] <= maxDiff) dsu.join(i, i + 1);
        }

        boolean[] res = new boolean[queries.length];
        int index = 0;
        for (int[] q : queries) {
            res[index++] = dsu.check(q[0], q[1]);
        }

        return res;
    }

    static class DisjointSet {
        int[] par;

        DisjointSet(int n) {
            this.par = new int[n];
            Arrays.fill(par, -1);
        }

        int find(int u) {
            return par[u] < 0 ? u : (par[u] = find(par[u]));
        }

        boolean join(int u, int v) {
            int x = find(u);
            int y = find(v);

            if (x == y) return false;

            par[x] += par[y];
            par[y] = x;
            return true;
        }

        boolean check(int u, int v) {
            return find(u) == find(v);
        }
    }
}
