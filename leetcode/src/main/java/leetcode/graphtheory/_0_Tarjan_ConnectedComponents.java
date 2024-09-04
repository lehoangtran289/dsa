package leetcode.graphtheory;

import codeforce.cf933_div3.A;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class _0_Tarjan_ConnectedComponents {

    static class Tarjan {
        private final int V; // number of vertices
        private int preCount; // preorder number counter, to track the order in which nodes are visited during DFS
        private final int[] low; // low number of v, An array where low[v] represents the smallest preorder number reachable from vertex v, including back edges
        private final boolean[] visited; // to check if v is visited
        private final List<Integer>[] graph; // to store given graph
        private final List<List<Integer>> sccComp; // to store all scc
        private final Stack<Integer> stack; // This stack is used during DFS to keep track of the visited vertices that have not yet been assigned to an SCC.

        public Tarjan(List<Integer>[] graph) {
            V = graph.length;
            this.graph = graph;
            low = new int[V];
            visited = new boolean[V];
            stack = new Stack<>();
            sccComp = new ArrayList<>();
        }

        /**
         * function to get all strongly connected components
         **/
        public List<List<Integer>> getSCComponents() {
            for (int v = 0; v < V; v++)
                if (!visited[v])
                    dfs(v);

            return sccComp;
        }

        public void dfs(int v) {
            low[v] = preCount++;
            visited[v] = true;
            stack.push(v);
            int min = low[v];
            for (int w : graph[v]) {
                if (!visited[w])
                    dfs(w);
                if (low[w] < min)
                    min = low[w];
            }
            if (min < low[v]) {
                low[v] = min;
                return;
            }
            List<Integer> component = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                component.add(w);
                low[w] = V;
            } while (w != v);
            sccComp.add(component);
        }

        int time = 0;
        static final int NIL = -1;
        List<List<Integer>> bridges = new ArrayList<>();
        void bridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent) {

            // Mark the current node as visited
            visited[u] = true;

            // Initialize discovery time and low value
            disc[u] = low[u] = ++time;

            // Go through all vertices adjacent to this
            // v is current adjacent of u
            for (int v : graph[u]) {
                // If v is not visited yet, then make it a child
                // of u in DFS tree and recur for it.
                // If v is not visited yet, then recur for it
                if (!visited[v]) {
                    parent[v] = u;
                    bridgeUtil(v, visited, disc, low, parent);

                    // Check if the subtree rooted with v has a
                    // connection to one of the ancestors of u
                    low[u] = Math.min(low[u], low[v]);

                    // If the lowest vertex reachable from subtree
                    // under v is below u in DFS tree, then u-v is
                    // a bridge
                    if (low[v] > disc[u]) {
                        bridges.add(List.of(u, v));
                    }
                }

                // Update low value of u for parent function calls.
                else if (v != parent[u])
                    low[u] = Math.min(low[u], disc[v]);
            }
        }


        // DFS based function to find all bridges. It uses recursive
        // function bridgeUtil()
        List<List<Integer>> bridge() {
            // Mark all the vertices as not visited
            boolean[] visited = new boolean[V];
            int[] disc = new int[V];
            int[] low = new int[V];
            int[] parent = new int[V];


            // Initialize parent and visited, and ap(articulation point) arrays
            for (int i = 0; i < V; i++) {
                parent[i] = NIL;
                visited[i] = false;
            }

            // Call the recursive helper function to find Bridges
            // in DFS tree rooted with vertex 'i'
            for (int i = 0; i < V; i++)
                if (!visited[i])
                    bridgeUtil(i, visited, disc, low, parent);

            return bridges;
        }
    }

    public static void main(String[] args) {
        int size = 8;
        List<Integer>[] graph = new List[size];
        for (int i = 0; i < size; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(1);
        graph[1].add(2);
        graph[1].add(5);
        graph[1].add(4);
        graph[2].add(3);
        graph[2].add(6);
        graph[3].add(2);
        graph[3].add(7);
        graph[4].add(0);
        graph[4].add(5);
        graph[5].add(6);
        graph[6].add(5);
        graph[7].add(3);
        graph[7].add(6);

        Tarjan tarjan = new Tarjan(graph);
        List<List<Integer>> sccComp = tarjan.getSCComponents();
        System.out.println(sccComp);
        System.out.println(tarjan.bridge());

        int size2 = 4;
        List<Integer>[] graph2 = new List[size2];
        for (int i = 0; i < size2; i++) {
            graph2[i] = new ArrayList<>();
        }
        graph2[0].add(1);
        graph2[1].add(2);
        graph2[2].add(3);
        Tarjan tarjan2 = new Tarjan(graph2);
        System.out.println(tarjan2.bridge());
    }
}
