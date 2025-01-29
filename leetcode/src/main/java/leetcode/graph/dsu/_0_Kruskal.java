package leetcode.graph.dsu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class _0_Kruskal {
    public static void main(String[] args) {
        int vertices = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        List<Edge> result = kruskal(vertices, edges);

        System.out.println("Edges in MST:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
        }
        System.out.println("Total weight: " + result.stream().mapToInt(e -> e.weight).sum());
    }

    public static List<Edge> kruskal(int n, List<Edge> edges) {
        List<Edge> result = new ArrayList<>();
        edges.sort(Comparator.comparingInt(e -> e.weight));

        DisjointSet ds = new DisjointSet(n);

        for (Edge e : edges) {
            int rootSrc = ds.find(e.src);
            int rootDest = ds.find(e.dest);

            if (rootSrc != rootDest) {
                result.add(e);
                ds.union(rootSrc, rootDest);
            }
        }

        return result;
    }
}

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

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
