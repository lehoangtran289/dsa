package leetcode.tree.spanningtree;

import java.util.Arrays;

public class M_684_RedundantConnection {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                findRedundantConnection(
                        new int[][]{{1, 2}, {1, 3}, {3, 1}})
                )
        );
    }

    public static int[] findRedundantConnection(int[][] edges) {
        DisjointSet dsu = new DisjointSet(edges.length + 1);

        int[] res = new int[2];
        for (int[] e : edges) {
            if (dsu.union(e[0], e[1])) {
                res[0] = e[0];
                res[1] = e[1];
            }
        }

        return res;
    }

    static class DisjointSet {
        private final int[] parent;

        DisjointSet(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) return parent[x];
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return true;

            parent[rootX] = rootY;
            return false;
        }
    }
}
