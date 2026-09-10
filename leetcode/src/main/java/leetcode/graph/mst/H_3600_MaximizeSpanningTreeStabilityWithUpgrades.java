package leetcode.graph.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class H_3600_MaximizeSpanningTreeStabilityWithUpgrades {

    /**
     * DSU
     * ---
     * TC: O(E logE), sorting
     * SC: O(E + n), n for dsu array
     */
    public static int maxStability(int n, int[][] edges, int k) {
        // sort by strength DESC
        Arrays.sort(edges, (a, b) -> {
            if (a[3] == b[3]) return Integer.compare(b[2], a[2]);
            return b[3] - a[3];
        });

        DisjointSet dsu = new DisjointSet(n);
        List<Integer> optionalEdges = new ArrayList<>();
        int res = 1 << 30;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int s = edge[2];
            int must = edge[3];

            if (must == 1) {
                if (!dsu.join(u, v)) return -1; // if a cycle forms -> invalid
                res = Math.min(res, s);
            } else {
                if (dsu.join(u, v)) optionalEdges.add(s);
            }
        }

        // if graph not connected -> invalid
        if (!dsu.isConnected()) return -1;

        // try upgrade optional edges
        for (int i = optionalEdges.size() - 1; i >= 0; --i) {
            if (k-- > 0) optionalEdges.set(i, optionalEdges.get(i) * 2);
            res = Math.min(res, optionalEdges.get(i));
        }

        return res;
    }

    static class DisjointSet {
        int[] lab;

        DisjointSet(int n) {
            this.lab = new int[n];
            Arrays.fill(lab, -1);
        }

        int find(int u) {
            return lab[u] < 0 ? u : (lab[u] = find(lab[u]));
        }

        boolean join(int u, int v) {
            int x = find(u);
            int y = find(v);

            if (x == y) return false;

            lab[x] += lab[y];
            lab[y] = x;
            return true;
        }

        /**
         * Check if graph is connected
         */
        boolean isConnected() {
            int root = find(0);
            for (int i = 1; i < lab.length; ++i) {
                if (root != find(i)) return false;
            }
            return true;
        }
    }
}
