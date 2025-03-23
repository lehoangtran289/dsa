package contest.weekly442;

import java.util.HashSet;
import java.util.Set;

public class Q2 {
    public static void main(String[] args) {
        System.out.println(numberOfComponents(new int[][]{{1, 2}, {1, 1}, {3, 4}, {4, 5}, {5, 6}, {7, 7}}, 1)); // 3
    }

    public static int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        Set<Integer>[] sets = new HashSet[n];
        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            sets[i] = new HashSet<>();
            for (int j : properties[i]) {
                sets[i].add(j);
            }
        }

        // Check each pair of nodes
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (intersect(sets[i], sets[j]) >= k) {
                    ds.union(i, j);
                }
            }
        }

        return ds.getCounts();
    }

    public static int intersect(Set<Integer> a, Set<Integer> b) {
        int res = 0;
        for (int n : b) {
            if (a.contains(n)) res++;
        }
        return res;
    }

    static class DisjointSet {
        private final int[] parent, rank;
        private int counts;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            counts = n;

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
            if (rank[rootU] == rank[rootV]) {
                rank[rootV]++;
                counts--;
            }
        }

        public int getCounts() {
            return counts;
        }
    }
}
