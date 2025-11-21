package leetcode.graph.mst;

class DisjointSet {
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
