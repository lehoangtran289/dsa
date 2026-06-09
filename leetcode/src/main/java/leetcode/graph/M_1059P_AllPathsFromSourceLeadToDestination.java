package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

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
}
