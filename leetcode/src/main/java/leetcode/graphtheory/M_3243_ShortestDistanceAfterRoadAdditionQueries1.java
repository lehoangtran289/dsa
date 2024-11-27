package leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class M_3243_ShortestDistanceAfterRoadAdditionQueries1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestDistanceAfterQueries(5, new int[][]{{2, 4}, {0, 2}, {0, 4}})));
    }

    public static int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
            if (i != n - 1) adj[i].add(i + 1);
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            adj[queries[i][0]].add(queries[i][1]);
            res[i] = bfs(n, adj);
        }

        return res;
    }

    private static int bfs(int n, List<Integer>[] adj) {
        int s = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        int[] d = new int[n];

        visited[s] = true;
        queue.add(s);
        d[s] = 0;
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int v : adj[u]) {
                if (!visited[v]) {
                    d[v] = d[u] + 1;
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return d[n - 1];
    }
}
