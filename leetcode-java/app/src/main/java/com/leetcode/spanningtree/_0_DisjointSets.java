package com.leetcode.spanningtree;

public class _0_DisjointSets {
    private final int[] parent;
    private final int[] rank;

    public _0_DisjointSets(int n) {
        parent = new int[n];
        rank = new int[n];

        // Initially, each element is its own set, and the rank is 0
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find the representative of the set that i belongs to
    public int find(int i) {
        // Path compression
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    // Union two sets by rank
    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI == rootJ) return;

        // Union by rank to keep the tree balanced
        if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else {
            // If ranks are the same, arbitrarily choose one as the root and increment its rank
            parent[rootI] = rootJ;
            rank[rootJ]++;
        }
    }

    public static void main(String[] args) {
        int n = 5; // Number of elements
        _0_DisjointSets ds = new _0_DisjointSets(n);

        // Example usage
        ds.union(0, 1);
        ds.union(2, 3);
        ds.union(0, 4);
        ds.union(4, 1);

        // Check if two elements belong to the same set
        System.out.println(ds.find(0) == ds.find(4)); // Should print true
        System.out.println(ds.find(1) == ds.find(3)); // Should print false
    }
}