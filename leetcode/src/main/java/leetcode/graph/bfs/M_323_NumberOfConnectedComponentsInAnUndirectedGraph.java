package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_323_NumberOfConnectedComponentsInAnUndirectedGraph {
    public static void main(String[] args) {
        System.out.println(countComponents(
                5,
                new int[][]{
                        {0, 1},
                        {1, 2},
                        {3, 4}
                }
        )); // 2
    }

    public static int countComponents(int n, int[][] edges) {
        // init adjacency list
        List<Integer>[] adj = new List[n];

        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // process
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; ++i) {
            if (visited[i]) continue;

            count++;
            bfs(adj, visited, i);
        }

        return count;
    }

    private static void bfs(List<Integer>[] adj, boolean[] visited, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int neighbor : adj[cur]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
