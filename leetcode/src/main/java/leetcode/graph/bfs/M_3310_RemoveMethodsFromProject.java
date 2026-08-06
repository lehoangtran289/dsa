package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_3310_RemoveMethodsFromProject {

    /**
     * TC: O(n + m), where n is the number of methods and m is the number of invocations
     * SC: O(n + m)
     */
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        boolean[] bugMethods = bfs(invocations, n, k);
        List<Integer> res = new ArrayList<>();

        // find all methods that invoke bug methods
        for (int[] inv : invocations) {
            int from = inv[0];
            int to = inv[1];

            // return all methods
            if (!bugMethods[from] && bugMethods[to]) {
                for (int i = 0; i < n; ++i) {
                    res.add(i);
                }
                return res;
            }
        }

        // return remaining methods
        for (int i = 0; i < n; ++i) {
            if (!bugMethods[i]) res.add(i);
        }
        return res;
    }

    private boolean[] bfs(int[][] invocations, int n, int k) {
        boolean[] res = new boolean[n];

        // build adjacent list
        List<Integer>[] adjList = new List[n];
        for (int i = 0; i < n; ++i) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] inv : invocations) {
            adjList[inv[0]].add(inv[1]);
        }

        // run bfs
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[k] = true;
        queue.add(k);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[cur] = true;

            for (int neighbor : adjList[cur]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return res;
    }
}
