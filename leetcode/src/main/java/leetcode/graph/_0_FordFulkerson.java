package leetcode.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Ford Fulkerson Algorithm for Maximum Flow Problem
 * --------------------------------
 * TC: O(E * max_flow)
 * SC: O(V + E)
 * --------------------------------
 */
public class _0_FordFulkerson {

    /**
     * M_1820_MaximumNumberOfAcceptedInvitations
     * --------------------------------
     * Using Ford-Fulkerson to solve Maximum Bipartite Matching problem
     */
    public int maximumInvitations(int[][] grid) {
        return fordFulkerson(grid);
    }

    private int fordFulkerson(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int totalNodes = m + n + 2;
        int source = 0;
        int sink = m + n + 1;

        // initialize residual graph - capacities, as a adj matrix -> size V x V
        // boys index: [1, m]
        // girls index: [m + 1, m + n]
        int[][] residualCap = new int[totalNodes][totalNodes];

        // connect source & sink
        for (int i = 0; i < m; ++i) {
            residualCap[source][i + 1] = 1;
        }
        for (int j = 0; j < n; ++j) {
            residualCap[m + 1 + j][sink] = 1;
        }

        // connect boys to girls
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    residualCap[i + 1][m + 1 + j] = 1;
                }
            }
        }

        int[] parent = new int[totalNodes];
        Arrays.fill(parent, -1);
        int maxFlow = 0;

        // while there is a path from source to sink
        while (bfs(residualCap, source, sink, parent)) {
            int pathFlow = 1; // since capacities are 1

            // update residual capacities of the edges and reverse edges
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualCap[u][v] -= pathFlow;
                residualCap[v][u] += pathFlow;
            }
            maxFlow += pathFlow; // add path flow to overall flow ~ increment matching count by 1
        }

        // find assignment
        System.out.println(Arrays.toString(getAssignment(residualCap, m, n)));

        return maxFlow;
    }

    private boolean bfs(
            int[][] residualGraph,
            int source,
            int sink,
            int[] parent
    ) {
        int totalNodes = residualGraph.length;
        boolean[] visited = new boolean[totalNodes];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < totalNodes; v++) {
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.offer(v);
                    parent[v] = u;
                    visited[v] = true;

                    if (v == sink) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int[] getAssignment(int[][] residualGraph, int m, int n) {
        int[] match = new int[m]; // match[i] = girl index or 0

        for (int i = 1; i <= m; i++) {
            match[i - 1] = 0; // default (unmatched)
            for (int j = 1; j <= n; j++) {

                // If (reverse edge) residualGraph[girl][boy] == 1 → boy matched with this girl
                if (residualGraph[m + j][i] == 1) {
                    match[i - 1] = j;
                    break;
                }
            }
        }

        return match;
    }
}
