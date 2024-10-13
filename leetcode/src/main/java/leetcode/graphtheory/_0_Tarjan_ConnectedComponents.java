package leetcode.graphtheory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _0_Tarjan_ConnectedComponents {

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

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = createGraph(n);
        for (List<Integer> connection : connections) {
            graph[connection.get(0)].add(connection.get(1));
            graph[connection.get(1)].add(connection.get(0));
        }
        Tarjan tarjan = new Tarjan(graph);
        return tarjan.bridge();
    }

    public static void main(String[] args) {
        int size = 9;
        List<Integer>[] graph = createGraph(size);
        graph[0].add(1);
        graph[1].add(2);
        graph[2].add(0);
        graph[0].add(3);
        graph[3].add(7);
        graph[4].add(3);
        graph[4].add(5);
        graph[5].add(6);
        graph[6].add(4);
        graph[2].add(8);
        graph[8].add(2);

        Tarjan tarjan = new Tarjan(graph);
        List<List<Integer>> sccComp = tarjan.getSCComponents();
        System.out.println(sccComp);
        System.out.println(tarjan.bridge());

        System.out.println("=====================================");

        int size2 = 4;
        List<Integer>[] graph2 = createGraph(size2);
        graph2[0].add(1);
        graph2[1].add(2);
        graph2[2].add(0);
        graph2[1].add(3);
        Tarjan tarjan2 = new Tarjan(graph2);
        System.out.println(tarjan2.getSCComponents());
        System.out.println(tarjan2.bridge());

        System.out.println("=====================================");

        int size3 = 2;
        List<Integer>[] graph3 = createGraph(size3);
        for (int i = 0; i < size3; i++) {
            graph3[i] = new ArrayList<>();
        }
        graph3[0].add(1);
        Tarjan tarjan3 = new Tarjan(graph3);
        System.out.println(tarjan3.bridge());

        System.out.println("=====================================");

        int size4 = 7;
        List<Integer>[] graph4 = createGraph(size4);
        addEdgeUndi(graph4, 0, 1);
        addEdgeUndi(graph4, 1, 2);
        addEdgeUndi(graph4, 0, 2);
        addEdgeUndi(graph4, 2, 3);
        addEdgeUndi(graph4, 3, 4);
        addEdgeUndi(graph4, 3, 5);
        addEdgeUndi(graph4, 5, 6);

        Tarjan tarjan5 = new Tarjan(graph4);
        System.out.println(tarjan5.getSCComponents());
        System.out.println(tarjan5.bridge());
        System.out.println(tarjan5.AP());
    }

    static class Tarjan {
        static final int NIL = -1;
        private final int V; // number of vertices
        private final int[] low; // low number of v, An array where low[v] represents the smallest preorder number reachable from vertex v, including back edges
        private final boolean[] visited; // to check if v is visited
        private final List<Integer>[] graph; // to store given graph
        private final List<List<Integer>> sccComp; // to store all scc
        private final Stack<Integer> stack; // This stack is used during DFS to keep track of the visited vertices that have not yet been assigned to an SCC.
        int num = 0;

        // ======================================
        // GET ALL STRONGLY CONNECTED COMPONENTS
        // ======================================
        List<List<Integer>> bridges = new ArrayList<>();
        // ======================================
        // GET ALL ARTICULATION POINTS
        // ======================================
        List<Integer> apList = new ArrayList<>();

        // ======================================
        // GET ALL BRIDGES
        // ======================================
        private int preCount; // preorder number counter, to track the order in which nodes are visited during DFS
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

        void bridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent) {
            visited[u] = true;
            disc[u] = low[u] = ++num; // Initialize discovery time and low value

            for (int v : graph[u]) {
                if (!visited[v]) {
                    parent[v] = u;
                    bridgeUtil(v, visited, disc, low, parent);

                    // Check if the subtree rooted with v has a connection to one of the ancestors of u
                    low[u] = Math.min(low[u], low[v]);

                    // If the lowest vertex reachable from subtree under v is below u in DFS tree, then u-v is a bridge
                    if (low[v] > disc[u]) {
                        bridges.add(List.of(u, v));
                    }
                }

                // Update low value of u for parent function calls.
                else if (v != parent[u])
                    low[u] = Math.min(low[u], disc[v]);
            }
        }

        // DFS based function to find all bridges. It uses recursive function bridgeUtil()
        public List<List<Integer>> bridge() {
            // Mark all the vertices as not visited
            boolean[] visited = new boolean[V];
            int[] disc = new int[V];
            int[] low = new int[V];
            int[] parent = new int[V];
            num = 0;

            // Initialize parent and visited, and ap(articulation point) arrays
            for (int i = 0; i < V; i++) {
                parent[i] = NIL;
                visited[i] = false;
            }

            // Call the recursive helper function to find Bridges in DFS tree rooted with vertex 'i'
            for (int i = 0; i < V; i++)
                if (!visited[i])
                    bridgeUtil(i, visited, disc, low, parent);

            return bridges;
        }

        void APUtil(int u, boolean[] visited, int[] disc, int[] low, int parent, boolean[] isAP) {
            int children = 0;
            visited[u] = true;
            disc[u] = low[u] = ++num;

            for (Integer v : graph[u]) {
                if (!visited[v]) {
                    children++;
                    APUtil(v, visited, disc, low, u, isAP);

                    // Check if the subtree rooted with v has a connection to one of the ancestors of u
                    low[u] = Math.min(low[u], low[v]);

                    // If u is not root and low value of one of its child is more than discovery value of u.
                    if (parent != -1 && low[v] >= disc[u])
                        isAP[u] = true;
                }

                // Update low value of u for parent function calls.
                else if (v != parent)
                    low[u] = Math.min(low[u], disc[v]);
            }

            // If u is root of DFS tree and has two or more children.
            if (parent == -1 && children > 1)
                isAP[u] = true;
        }

        public List<Integer> AP() {
            boolean[] visited = new boolean[V];
            int[] disc = new int[V];
            int[] low = new int[V];
            boolean[] isAP = new boolean[V];
            num = 0;
            int par = -1;

            // Adding this loop so that the
            // code works even if we are given
            // disconnected graph
            for (int u = 0; u < V; u++)
                if (!visited[u])
                    APUtil(u, visited, disc, low, par, isAP);

            for (int u = 0; u < V; u++)
                if (isAP[u])
                    apList.add(u);

            return apList;
        }
    }
}
