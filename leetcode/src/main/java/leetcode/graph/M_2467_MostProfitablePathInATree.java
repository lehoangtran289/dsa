package leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class M_2467_MostProfitablePathInATree {
    public static void main(String[] args) {
        System.out.println(mostProfitablePath(
                new int[][]{{0, 1}, {1, 2}, {1, 3}, {3, 4}},
                3,
                new int[]{-2, 4, 2, -4, 6}
        ));
    }

    public static int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = edges.length + 1;

        // construct adjacency list
        List<Integer>[] adj = new List[n];

        for (int i = 0; i < adj.length; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // BFS to find the path from bob to 0
        Map<Integer, Integer> bobPath = bobPath(adj, bob);

        // BFS to find dist from Alice to all leaf nodes and retrieve max profit
        int maxProfit = amount[0];

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;

        // track the path
        Integer[] prev = new Integer[n]; // track path from src to all reachable vertices
        Arrays.fill(prev, -1);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; ++i) {
                int u = queue.poll();
            }
        }

        return maxProfit;
    }

    private static Map<Integer, Integer> bobPath(List<Integer>[] adj, int bob) {
        int n = adj.length;

        // track the path
        Integer[] prev = new Integer[n]; // track path from src to all reachable vertices
        Arrays.fill(prev, -1);

        boolean[] visited = new boolean[adj.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(bob);
        visited[bob] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (u == 0) break;

            for (int v : adj[u]) {
                if (!visited[v]) {
                    prev[v] = u;
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }

        Map<Integer, Integer> path = new HashMap<>();
        List<Integer> pathList = new ArrayList<>();
        int cur = 0;
        while (cur != -1) {
            pathList.add(cur);
            cur = prev[cur]; // backtrack
        }

        for (int i = 0; i < pathList.size(); ++i) {
            path.put(pathList.get(i), pathList.size() - i - 1);
        }
        return path;
    }
}
