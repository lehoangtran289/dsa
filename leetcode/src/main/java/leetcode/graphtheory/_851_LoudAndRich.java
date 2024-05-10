package leetcode.graphtheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _851_LoudAndRich {
    public static void main(String[] args) {
        int[][] richer = new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = new int[]{3, 2, 5, 4, 6, 1, 7, 0};
        System.out.println(Arrays.toString(loudAndRich(richer, quiet)));
    }

    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[] answer = new int[n];
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < adj.length; ++i) {
            adj[i] = new ArrayList<>();
        }
        for (int[] ints : richer) {
            adj[ints[1]].add(ints[0]);
        }
        for (int i = 0; i < n; ++i) {
            answer[i] = dfs(adj, quiet, i, new boolean[n]);
        }
        return answer;
    }

    private static int dfs(List<Integer>[] adj, int[] quiet, int u, boolean[] visited) {
        visited[u] = true;
        int minIndex = u;
        for (int v : adj[u]) {
            if (!visited[v]) {
                int candidate = dfs(adj, quiet, v, visited);
                if (quiet[candidate] < quiet[minIndex]) {
                    minIndex = candidate;
                }
            }
        }
        return minIndex;
    }
}
