package leetcode.contest.weekly426;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Q3_MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {
    public static void main(String[] args) {
        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}};
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}};

        System.out.println(Arrays.toString(maxTargetNodes(edges1, edges2, 2)));
    }

    public static int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        List<Integer>[] gr1 = createGraph(edges1.length + 1);
        for (int[] edge : edges1) addEdgeUndi(gr1, edge[0], edge[1]);

        List<Integer>[] gr2 = createGraph(edges2.length + 1);
        for (int[] edge : edges2) addEdgeUndi(gr2, edge[0], edge[1]);

        int[] reachableIn1 = new int[gr1.length];
        for (int i = 0; i < gr1.length; ++i) {
            reachableIn1[i] = bfs(gr1, i, k);
        }

        int[] reachableIn2 = new int[gr2.length];
        for (int i = 0; i < gr2.length; ++i) {
            reachableIn2[i] = bfs(gr2, i, k - 1);
        }

        int[] res = new int[gr1.length];
        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < gr2.length; ++j) {
                res[i] = Math.max(res[i], reachableIn1[i] + reachableIn2[j]);
            }
        }

        return res;
    }

    // count number of nodes within distance k
    private static int bfs(List<Integer>[] adj, int start, int k) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[adj.length];
        int res = 0;

        queue.add(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int u = cur[0];
            int distance = cur[1];

            if (distance <= k) res++;
            else break;

            for (int v : adj[u]) {
                if (!visited[v]) {
                    queue.add(new int[]{v, distance + 1});
                    visited[v] = true;
                }
            }
        }

        return res;
    }

    private static List<Integer>[] createGraph(int n) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        return graph;
    }

    private static void addEdgeUndi(List<Integer>[] graph, int from, int to) {
        graph[from].add(to);
        graph[to].add(from);
    }
}
