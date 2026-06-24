package leetcode.graph.mst;

import java.util.Arrays;

/**
 * Disjoint Set with Set size management
 * If u is root -> lab[u] = -c  (set u has size c)
 */
class DisjointSet {
    private final int[] lab;

    DisjointSet(int n) {
        this.lab = new int[n];
        Arrays.fill(lab, -1);
    }

    /**
     * Find the representative of the set that contains u
     * With Path compression
     * ---
     * TC: O(a(n)) ~ O(1)
     */
    public int find(int u) {
        return lab[u] < 0 ? u : (lab[u] = find(lab[u]));
    }

    /**
     * Join the sets that contain u and v
     * ---
     * TC: O(a(n)) ~ O(1)
     */
    public boolean join(int u, int v) {
        int x = find(u);
        int y = find(v);
        if (x == y) return false;

        // Micro optimization: Attach the smaller tree to the root of the larger tree
        // if (lab[x] > lab[y]) swap(x, y);

        lab[x] += lab[y]; // Set size of x += set size of y
        lab[y] = x;
        return true;
    }

    /**
     * Check if u and v are in the same set
     */
    public boolean check(int u, int v) {
        return find(u) == find(v);
    }

    /**
     * Get the size of the set that contains u
     */
    public int getSize(int u) {
        return -lab[find(u)];
    }

}
