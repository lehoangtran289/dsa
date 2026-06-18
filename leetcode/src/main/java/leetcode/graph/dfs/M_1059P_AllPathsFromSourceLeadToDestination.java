package leetcode.graph.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_1059P_AllPathsFromSourceLeadToDestination {

    /**
     * Coloring DFS
     * Trick: we need to check if there is a cycle in the path, if there is a cycle, we can never reach the destination, so we can return false
     * ---
     * Time: O(V + E)
     * Space: O(V + E)
     */
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        // coloring dfs
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        return dfs(graph, source, destination, new int[n]);
    }

    private boolean dfs(
            List<Integer>[] graph,
            int node,
            int dest,
            int[] states
    ) {
        // if states[node] = BLACK -> cross edge, not cycle
        if (states[node] != 0) return states[node] == 2;

        // leaf node must be dest
        if (graph[node].isEmpty()) return node == dest;

        states[node] = 1;
        for (int nextNode : graph[node]) {
            if (!dfs(graph, nextNode, dest, states)) {
                return false;
            }
        }

        states[node] = 2;
        return true;
    }

    /**
     * Topological Sort BFS (backward from dest)
     * Trick: we traverse from dest to source, and check if we can reach source,
     * If we can reach source, it means all paths from source lead to dest (since only outdegree = 0 can be in queue)
     * otherwise, there is a path from source that leads to a leaf node that is not dest
     * ---
     */
    public boolean leadsToDestination2(int n, int[][] edges, int source, int destination) {
        // init graph && outdegree
        List<Integer>[] graph = new ArrayList[n];
        int[] outdegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            if (edge[0] == destination) { // dest cannot have outdegree
                return false;
            }

            graph[edge[1]].add(edge[0]);
            outdegree[edge[0]]++;
        }

        // backward bfs
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(destination);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == source) // Only outdegree = 0 can be in queue -> if we reach source -> all paths is TRUE
                return true;

            for (int neighbor : graph[node]) {
                outdegree[neighbor]--;

                if (outdegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }
}
