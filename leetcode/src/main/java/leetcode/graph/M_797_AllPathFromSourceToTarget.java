package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

public class M_797_AllPathFromSourceToTarget {
    public static void main(String[] args) {
        System.out.println(allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> trace = new ArrayList<>();

        trace.add(0);
        dfs(graph, 0, trace, res);
        return res;
    }

    private static void dfs(int[][] graph, int u, List<Integer> trace, List<List<Integer>> res) {
        if (u == graph.length - 1) { // if dest reached
            res.add(new ArrayList<>(trace));
        }

        for (int v : graph[u]) {
            trace.add(v);
            dfs(graph, v, trace, res);
            trace.remove(trace.size() - 1); // backtrack to visit other paths
        }
    }
}
