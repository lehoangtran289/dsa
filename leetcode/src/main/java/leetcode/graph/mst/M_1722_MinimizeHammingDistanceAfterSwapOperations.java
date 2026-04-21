package leetcode.graph.mst;

import java.util.HashMap;
import java.util.Map;

public class M_1722_MinimizeHammingDistanceAfterSwapOperations {
    public static void main(String[] args) {
        System.out.println(minimumHammingDistance(
                new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 5}, new int[][]{{0, 1}, {2, 3}})
        ); // 1
    }

    /**
     * Union Find + HashMap
     * Idea: Group indices that can be swapped together
     * For each group, count the frequency of each element in target and source arrays.
     * ----
     * TC: O(n + alpha * m); m = allowedSwaps.length
     * SC: O(n) for Union Find and HashMap
     */
    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int res = 0;
        int n = source.length;
        UnionFind uf = new UnionFind(n);

        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        // keeps track of freq of each element in group
        Map<Integer, Map<Integer, Integer>> groups = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            int root = uf.find(i);
            groups.putIfAbsent(root, new HashMap<>());

            Map<Integer, Integer> freq = groups.get(root);
            freq.put(target[i], freq.getOrDefault(target[i], 0) + 1);
        }

        for (int i = 0; i < n; ++i) {
            int root = uf.find(i);
            Map<Integer, Integer> freq = groups.get(root);

            if (freq.getOrDefault(source[i], 0) > 0) {
                freq.put(source[i], freq.get(source[i]) - 1);
            } else {
                res++;
            }
        }

        return res;
    }

    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];

            for (int v = 0; v < n; v++) {
                parent[v] = v;
            }
        }

        int find(int v) {
            return v == parent[v] ? v : (parent[v] = find(parent[v]));
        }

        void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return;
            parent[rootV] = rootU;
        }
    }
}
