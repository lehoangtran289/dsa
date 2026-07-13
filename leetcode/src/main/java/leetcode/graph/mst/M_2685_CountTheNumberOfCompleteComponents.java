package leetcode.graph.mst;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_2685_CountTheNumberOfCompleteComponents {

    public int countCompleteComponents(int n, int[][] edges) {
        // build disjoint set
        DisjointSet dsu = new DisjointSet(n);
        for (int[] edge : edges) {
            dsu.join(edge[0], edge[1]);
        }

        // count edges in each component
        Map<Integer, Integer> edgeCountMap = new HashMap<>();
        for (int[] edge : edges) {
            int root = dsu.find(edge[0]);
            edgeCountMap.put(root, edgeCountMap.getOrDefault(root, 0) + 1);
        }

        // traverse all roots to check for completed graph
        int res = 0;
        for (int root = 0; root < n; ++root) {
            if (dsu.find(root) != root) continue; // not a root -> skip

            int nodeCnt = dsu.size(root);
            int expectedEdgeCnt = nodeCnt * (nodeCnt - 1) / 2;
            int edgeCnt = edgeCountMap.getOrDefault(root, 0);

            if (expectedEdgeCnt == edgeCnt) res++;
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
            int rootU = find(u);
            int rootV = find(v);

            if (rootU == rootV) return false;

            par[rootU] += par[rootV];
            par[rootV] = rootU;
            return true;
        }

        int size(int u) {
            if (par[u] > 0) return size(find(u));
            return -par[u];
        }
    }
}
