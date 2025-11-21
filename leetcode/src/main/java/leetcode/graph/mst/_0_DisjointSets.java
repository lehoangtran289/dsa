package leetcode.graph.mst;

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

    public static void main(String[] args) {
        int n = 5; // Number of elements
        DisjointSet ds = new DisjointSet(n);

        // Example usage
        ds.union(0, 1);
        ds.union(2, 3);
        ds.union(0, 4);
        ds.union(4, 1);

        // Check if two elements belong to the same set
        System.out.println(ds.find(0) == ds.find(4)); // Should print true
        System.out.println(ds.find(1) == ds.find(3)); // Should print false
    }

    // Find the representative of the set that i belongs to
    public int find(int v) {
        // Path compression
        return v == parent[v] ? v : (parent[v] = find(parent[v]));
    }

    // Union two sets by rank
    public void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) return;

        // Union by rank to keep the tree balanced
        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else {
            // If ranks are the same, arbitrarily choose one as the root and increment its rank
            parent[rootU] = rootV;
            rank[rootV]++;
        }
    }
}