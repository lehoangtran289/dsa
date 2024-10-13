package leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class _0_BFS_CountComponents {

    public static void main(String[] args) {
        Graph g = new Graph(13);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 0);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(2, 3);
        g.addEdge(2, 7);
        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(3, 6);
        g.addEdge(4, 0);
        g.addEdge(4, 3);
        g.addEdge(5, 3);
        g.addEdge(5, 8);
        g.addEdge(6, 3);
        g.addEdge(6, 7);
        g.addEdge(7, 2);
        g.addEdge(7, 6);
        g.addEdge(7, 8);
        g.addEdge(7, 9);
        g.addEdge(8, 5);
        g.addEdge(8, 7);
        g.addEdge(9, 7);
        g.addEdge(10, 11);
        g.addEdge(11, 10);
        g.addEdge(12, 12);

        System.out.println("Number of components: " + g.countComponents());
    }

    static class Graph {
        private final int V; // number of vertices
        private final ArrayList<Integer>[] adj; // adjacency list
        private final Set<Integer> visited = new HashSet<>();
        private int numComponents = 0;

        // -----------------------------------------------------------------

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int u, int v) {
            adj[u].add(v);
        }

        public void bfs(int s) {
            numComponents++;
            Deque<Integer> queue = new ArrayDeque<>();

            visited.add(s);
            queue.add(s);
            while (!queue.isEmpty()) {
                int u = queue.remove();
                for (int v : adj[u]) {
                    if (!visited.contains(v)) {
                        visited.add(v);
                        queue.add(v);
                    }
                }
            }
        }

        public int countComponents() {
            for (int i = 0; i < V; i++) {
                if (!visited.contains(i)) {
                    bfs(i);
                }
            }
            return numComponents;
        }
    }
}
