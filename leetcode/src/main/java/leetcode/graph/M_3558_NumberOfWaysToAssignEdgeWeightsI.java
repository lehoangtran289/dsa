package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

public class M_3558_NumberOfWaysToAssignEdgeWeightsI {

    private static final int MOD = (int) 1e9 + 7;

    /**
     * DFS + Quick Power
     * ---
     * Time: O(n) to build graph + O(n) to get max depth + O(log(maxDepth)) for quick power
     * Space: O(n) for graph + O(n) for recursion stack
     */
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        // init graph
        List<Integer>[] tree = new List[n + 1];
        for (int i = 1; i <= n; ++i) tree[i] = new ArrayList<>();
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        // get max tree depth
        int maxDepth = getMaxDepth(tree, 1, 0);

        // quick pow
        return pow(2, maxDepth - 1);
    }

    /**
     * DFS to get max depth of tree. Note: use parent to avoid infinite loop of undirected graph
     */
    private int getMaxDepth(List<Integer>[] tree, int node, int parent) {
        int maxDepth = 0;
        for (int child : tree[node]) {
            if (child == parent) continue;
            maxDepth = Math.max(maxDepth, 1 + getMaxDepth(tree, child, node));
        }

        return maxDepth;
    }

    /**
     * Quick Power with MOD
     * ---
     * Time: O(log(power))
     */
    private int pow(int x, int power) {
        long res = 1;
        long base = x;

        while (power > 0) {
            if (power % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            power >>= 1;
        }
        return (int) res;
    }
}
