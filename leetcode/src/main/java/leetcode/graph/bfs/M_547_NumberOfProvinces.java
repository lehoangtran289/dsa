package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_547_NumberOfProvinces {

    /**
     * BFS
     * -----------------------
     * TC: O(n^2)
     * SC: O(n)
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int res = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                res++;
                bfs(isConnected, visited, i);
            }
        }

        return res;
    }

    private void bfs(
            int[][] isConnected,
            boolean[] visited,
            int node
    ) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < isConnected.length; ++i) {
                if (isConnected[cur][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
