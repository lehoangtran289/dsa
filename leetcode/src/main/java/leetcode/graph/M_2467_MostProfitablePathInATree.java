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
                new int[][]{{0, 1}, {1, 2}, {2, 3}},
                3,
                new int[]{-5644, -6018, 1188, -8502}
        ));

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

        // BFS to find the path from bob to 0 --> < node, time >
        Map<Integer, Integer> bobPath = bobPath(adj, bob);

        // BFS to find dist from Alice to all leaf nodes and retrieve max profit
        int maxProfit = Integer.MIN_VALUE;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        queue.add(new int[]{0, amount[0], 0}); // <node, profit, time>
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0];
            int profit = cur[1];
            int time = cur[2];

            for (int neighbor : adj[node]) {
                if (visited[neighbor]) continue;

                visited[neighbor] = true;
                int neighborTime = time + 1;
                int neighborProfit = profit;

                if (bobPath.containsKey(neighbor)) {
                    int bobTime = bobPath.get(neighbor);

                    if (bobTime == neighborTime) {              // meet at same node
                        neighborProfit += amount[neighbor] / 2;
                    } else if (bobTime > neighborTime) {        // Alice reaches this node before Bob
                        neighborProfit += amount[neighbor];
                    }
                } else {
                    neighborProfit += amount[neighbor];
                }
                queue.add(new int[]{neighbor, neighborProfit, neighborTime});
            }

            // left node
            if (adj[node].size() == 1 && node != 0) {
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }

    /**
     * BFS to find the path from bob to 0. </br>
     *
     * @return < node, time >
     */
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

        // construct path
        Map<Integer, Integer> result = new HashMap<>();

        List<Integer> pathList = new ArrayList<>();
        int cur = 0;
        while (cur != -1) {
            pathList.add(cur);
            cur = prev[cur]; // backtrack
        }

        for (int i = 0; i < pathList.size(); ++i) {
            result.put(pathList.get(i), pathList.size() - i - 1);
        }
        return result;
    }
}
