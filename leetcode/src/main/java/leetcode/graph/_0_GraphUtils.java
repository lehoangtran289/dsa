package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

public class _0_GraphUtils {
    public static List<Integer>[] createGraph(int n) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        return graph;
    }

    public static void addEdgeDi(List<Integer>[] graph, int from, int to) {
        graph[from].add(to);
        graph[to].add(from);
    }

    public static void addEdgeUndi(List<Integer>[] graph, int from, int to) {
        graph[from].add(to);
        graph[to].add(from);
    }

    static class Graph {
        private final int V; // number of vertices
        private final ArrayList<Integer>[] adj; // adjacency list

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdgeDirected(int u, int v) {
            adj[u].add(v);
        }

        public void addEdgeUndirected(int u, int v) {
            adj[u].add(v);
            adj[v].add(u);
        }
    }
}
