package leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class H_2872_MaximumNumberOfKDivisibleComponents {

    /**
     * DFS
     * --------------------------------
     * Idea: if leaf node % k == 0 -> new component
     * else merge sum with parent
     * --------------------------------
     * TC: O(n) - DFS traversal of tree once
     * SC: O(n) - adjacency list + recursion stack
     * --------------------------------
     */
    private List<Integer>[] adj;
    private int[] values;
    private int k;
    private int totalComponents;

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.values = values;
        this.k = k;
        this.totalComponents = 0;

        // init adj list
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; ++i) adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        this.adj = adj;

        dfs(0, -1);
        return totalComponents;
    }

    private long dfs(int cur, int par) {
        long curSum = values[cur];

        for (int next : adj[cur]) {
            if (next != par) {
                curSum += dfs(next, cur);
            }
        }

        // merge with parent if not divisible by k
        if (curSum % k != 0) {
            return curSum;
        }

        // else form a new component
        totalComponents++;
        return 0;
    }

    /**
     * BFS / Topological Sort
     * --------------------------------
     * Idea: if leaf node % k == 0 -> new component
     * else merge sum with parent
     * --------------------------------
     * TC: O(n) - BFS traversal of tree once
     * SC: O(n) - adjacency list + indegree + sum array + queue
     */

    public int maxKDivisibleComponents2(int n, int[][] edges, int[] values, int k) {
        // edge cases
        if (n < 2) return 1;

        int res = 0;

        // init adj list and indegree
        List<Integer>[] adj = new List[n];
        int[] indegree = new int[n];
        long[] lValues = new long[n]; // accumulated sum values from leaves to root

        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
            lValues[i] = values[i];
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            indegree[edge[0]]++;
            indegree[edge[1]]++;
        }

        // init queue
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (indegree[i] == 1) queue.add(i);
        }

        // traverse
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            long curSum = 0;

            if (lValues[cur] % k == 0) {
                res++;
            } else {
                curSum = lValues[cur];
            }

            for (int next : adj[cur]) {
                if (indegree[next] == 0 || next == cur) continue;

                lValues[next] += curSum;
                indegree[next]--;

                if (indegree[next] == 1) {
                    queue.add(next);
                }
            }
        }

        return res;
    }
}
